package coda.orderService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.model.UpdateOptions;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.beans.factory.annotation.Autowired;

import coda.datalayer.CodaApiAccess;
import coda.datalayer.MongoDatabaseAccess;
import coda.model.order.ClassifierParameter;
import coda.model.order.EvaluationStatus;
import coda.model.order.EvaluationStatusList;
import coda.model.order.EvaluationStatusWrapper;
import coda.model.evaluation.Evaluations;
import coda.model.order.Order;
import coda.model.order.Task;
import coda.shared.properties.Properties;
import coda.shared.logging.ILogging;

import static com.mongodb.client.model.Filters.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class OrderService implements IOrderService {
    @Autowired
    private Properties properties;

    @Autowired
    private MongoDatabaseAccess mongoDatabaseAccess;

    @Autowired
    private ILogging log;

    public OrderService() {
        timer.schedule(new RemindTask(), 10 * 1000, 60 * 1000); //first update after 10s, updates every 60s.
    }

    private String loadClassifiers() {
        String classifiersJson = "";
        try {
            classifiersJson = CodaApiAccess.get("listClassifiers");
        } catch (Exception e) {
            log.exception(e);
            log.error("Classifiers could not be loaded. Loading local defaults instead.");
            classifiersJson = CodaApiAccess.fileCall("classifiers.json");
        }

        return classifiersJson;

    }

    private static String loadEvaluationList() {
        String evaluationListJson = "";
        try {
            evaluationListJson = CodaApiAccess.get("listAllEvaluationStatus");
        } catch (Exception e) {
            evaluationListJson = CodaApiAccess.fileCall("allEvaluationStatus.json");
        }
        return evaluationListJson;
    }

    private String loadResult(UUID orderId) {
        String resultJson = "";
        try {
            List<String[]> params = new LinkedList<String[]>();
            params.add(new String[]{ "id", orderId.toString() });
            resultJson = CodaApiAccess.get("getResults", params);
        } catch (Exception e) {
            log.exception(e);
            log.error("Evaluation List could not be loaded. Loading local defaults instead.");
            resultJson = CodaApiAccess.fileCall("allEvaluationStatus.json");
        }
        return resultJson;
    }

    private String loadValidationMethodExample() {
        String validationMethodJson = null;
        validationMethodJson = CodaApiAccess.fileCall("validationmethods.json");
        return validationMethodJson;
    }

    private String loadEvaluationMetricsExample() {
        String evaluationMetricsJson = null;
        evaluationMetricsJson = CodaApiAccess.fileCall("evaluationmetrics.json");
        return evaluationMetricsJson;
    }

    private String loadDatasetsExample() {
        String datasetsJson = null;
        datasetsJson = CodaApiAccess.fileCall("datasets.json");
        return datasetsJson;
    }

    @Override
    public String GetStatus(UUID orderId) {
        List<String[]> parameter = new LinkedList<String[]>();
        String p = orderId.toString();
        parameter.add(new String[] { "id", p });

        try {
            return CodaApiAccess.get("getStatus", parameter);
        } catch(Exception e) {
            log.exception(e);
        }
        return null;
    }

    @Override
    public String GetOutput(UUID taskId) {
        return null;
    }

    private List<Order> getOrdersFromDatabase() {
        List<Order> ordersFromDatabase = new LinkedList<Order>();
        mongoDatabaseAccess.invokeForAll(
            mongoDatabaseAccess.getOrdersCollection(), 
            (json) -> { ordersFromDatabase.add(new Order(json)); }
        );

        return ordersFromDatabase;
    }

    private List<Order> getOrdersFromCodaServer() {
        List<Order> ordersFromDatabase = new LinkedList<Order>();
        mongoDatabaseAccess.invokeForAll(
            mongoDatabaseAccess.getOrdersCollection(), 
            (json) -> { ordersFromDatabase.add(new Order(json)); }
        );

        return ordersFromDatabase;
    }

    Timer timer = new Timer(true);
    private EvaluationStatusList evaluationStatusList = null;
    String lockObject = "";
    Boolean loadedFromProperties = false;

    class RemindTask extends TimerTask {
        public void run() {
            if(loadedFromProperties == false) {
                timer.schedule(new RemindTask(), 10 * 1000, 
                        properties.getOrderStatusUpdateIntervall() * 1000); 
                loadedFromProperties = true;
            }
            updateEvaluationStatusList();        
        }
    }

    private void updateEvaluationStatusList() {
        String allOnlineOrders = loadEvaluationList();

        ObjectMapper mapper = new ObjectMapper();

        try {
            synchronized(lockObject) {
                evaluationStatusList = mapper.readValue(allOnlineOrders, EvaluationStatusList.class);
            }
        } catch (JsonGenerationException e) {
            log.exception(e);
        } catch (JsonMappingException e) {
            log.exception(e);
        } catch (IOException e) {
            log.exception(e);
        }
        evaluationStatusList.buildOrderToStatusMap();
    }

    @Override
    public List<Order> getOrders(Boolean includeUnknownOrders) {
        List<Order> ordersFromDatabase = new LinkedList<Order>();
        ordersFromDatabase.addAll(getOrdersFromDatabase());

        String status = "";
        for (Order order : ordersFromDatabase) {
            synchronized(lockObject) {
                status = evaluationStatusList.getStatus(order.getEvaluationId());
            }
            if(status != null && status != order.getStatus()) {
                order.setStatus(status);
                saveOrder(order);
            }
        }
        
        return ordersFromDatabase;
    }

    @Override
    public Evaluations getResult(UUID orderId) {
        ObjectMapper mapper = new ObjectMapper();
        Evaluations orderResult = null;
        String resultJson = loadResult(orderId);
        
        try {
            orderResult = mapper.readValue(resultJson, Evaluations.class);
        } catch (JsonGenerationException e) {
            log.exception(e);
        } catch (JsonMappingException e) {
            log.exception(e);
        } catch (IOException e) {
            log.exception(e);
        }

        return orderResult;
    }

    

    @Override
    public Order getOrder(UUID id) {
        return new Order(mongoDatabaseAccess.getFirst(mongoDatabaseAccess.getOrdersCollection(), eq("_id", id.toString())));
    }

    @Override
    public void saveOrder(Order order) {
        mongoDatabaseAccess.upsert(mongoDatabaseAccess.getOrdersCollection(), order.get_Id().toString(), order.getJson());
    }
    
    @Override
    public void startOrder(Order order) {
        if(order.getStatus().compareTo("new") == 0) {
            String json = createOrderJson(order);
            try {
                log.debug(json);
                String retVal = CodaApiAccess.post("launchEvaluation", json);
                order.setEvaluationId(UUID.fromString(retVal));
                order.setStatus("started");
            } catch (Exception e) {
                log.exception(e);
            }
        }

        // for (Task task : order.getTasks()) {
        //     if(task.getStatus().compareTo("new") == 0) {
        //         String json = createTaskJson(task);
        //         log.debug(json);
        //         try {
        //             String retVal = CodaApiAccess.post("launchClassifier", json);
        //             task.setId(UUID.fromString(retVal));
        //             log.debug(retVal);
        //         } catch (Exception e) {
        //             log.exception(e);
        //         }
        //     }
        // }
    }

    private String createParameterString(List<ClassifierParameter> parameters) {
        String retVal = "";
        for (ClassifierParameter parameter : parameters) {
            if(retVal.length() > 0) {
                retVal += ",\n";
            }
            retVal += "\"" + parameter.getName() + "\":\"" + parameter.getValue() + "\"";
        }
        return retVal + "\n";
    }

    private String createTaskJson(Task task) {
        String retVal = "[{\n" +
            "\"datasetId\": \"" + task.getDataset().getId() + "\",\n" +
            "\"classifierName\": \"" + task.getClassifier().getName() + "\",\n" +
            "\"hyperParams\":{\n"
                + createParameterString(task.getClassifier().getParameters()) + 
            "},\n" +
            "\"validationMethod\":{\n" +
                "\"name\":\"" + task.getValidationMethod().getName() + "\"\n" +
            "},\n" +
            "\"evaluationMetric\":\"" + task.getMetric().getId() + "\"\n" +
            "}]";

        return retVal;
    }      

    private String createOrderJson(Order order) {
        String retVal = "[\n{\n";

        retVal += "\"datasetId\":\"" + order.getTasks().get(0).getDataset().getId() + "\",\n";
        retVal += "\"classifiers\":[" + createClassifierJson(order.getTasks()) + "],\n";
        retVal += "\"validationMethod\":" + createValidationMethodJson(order.getTasks().get(0)) + "\n},\n";
        retVal += "\"evaluationMetric\":\"" + order.getTasks().get(0).getMetric().getId() + "\"\n";

        retVal += "}\n]";

        return retVal;
    }

    private String createValidationMethodJson(Task task) {
        String retVal = "{\n\"name\":\"" + task.getValidationMethod().getName() + "\",\n";
        retVal += "\"params\":{\n";

        // Validation Method Parameters are not supportet at this time.

        retVal += "\n}\n";
        return retVal;
    }

    private String createClassifierJson(List<Task> tasks) {
        String retVal = "";

        String classifierJson = "";
        for (Task task : tasks) {
            if(classifierJson.length() > 0) {
                classifierJson += ",\n";
            }
            classifierJson += "{\n";
            classifierJson += "\"name\":\"" + task.getClassifier().getName() + "\",\n";
            classifierJson += "\"params\": {\n";
            
            String parameterString = "";
            for (ClassifierParameter parameter : task.getClassifier().getParameters()) {
                if(parameterString.length() > 0) {
                    parameterString += ",\n";
                }
                parameterString += "\"" + parameter.getName() + "\":\"" + parameter.getValue() + "\"";
            }

            classifierJson += parameterString + "\n";

            classifierJson += "}\n}";
        }
        retVal += "\n" + classifierJson;

        return retVal;
    }
}
