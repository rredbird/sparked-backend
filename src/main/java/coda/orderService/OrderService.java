package coda.orderService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.beans.factory.annotation.Autowired;

import coda.datalayer.CodaApiAccess;
import coda.datalayer.MongoDatabaseAccess;
import coda.model.order.EvaluationStatus;
import coda.model.order.EvaluationStatusList;
import coda.model.order.EvaluationStatusWrapper;
import coda.model.order.Order;
import coda.model.order.OrderResult;
import coda.model.order.Task;
import coda.shared.properties.Properties;
import coda.shared.logging.ILogging;

import static com.mongodb.client.model.Filters.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class OrderService implements IOrderService {
    @Autowired
    private Properties properties;

    @Autowired
    private MongoDatabaseAccess mongoDatabaseAccess;

    @Autowired
    private ILogging log;

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

    private String loadEvaluationList() {
        String evaluationListJson = "";
        try {
            evaluationListJson = CodaApiAccess.get("listAllEvaluationStatus");
        } catch (Exception e) {
            log.exception(e);
            log.error("Evaluation List could not be loaded. Loading local defaults instead.");
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
    public void RunTask(UUID taskId) {

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

    @Override
    public List<Order> getOrders(Boolean includeUnknownOrders) {
        List<Order> ordersFromDatabase = new LinkedList<Order>();
        mongoDatabaseAccess.invokeForAll(
            mongoDatabaseAccess.getOrdersCollection(), 
            (json) -> { ordersFromDatabase.add(new Order(json)); }
        );

        String allOnlineOrders = loadEvaluationList();

        EvaluationStatusList evaluationStatusList = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            evaluationStatusList = mapper.readValue(allOnlineOrders, EvaluationStatusList.class);
        } catch (JsonGenerationException e) {
            log.exception(e);
        } catch (JsonMappingException e) {
            log.exception(e);
        } catch (IOException e) {
            log.exception(e);
        }

        for (Order order : ordersFromDatabase) {
            if(evaluationStatusList.containsOrder(order.getId())) {
                //order.setOrderStatus(evaluationStatusList.getStatus(order.getId()));
            }
        }
        if(includeUnknownOrders) {
            for(EvaluationStatusWrapper evaluationStatusWrapper : evaluationStatusList.getStatusList()) {
                Order newOrder = new Order(evaluationStatusWrapper.getEvaluationStatus().get(0).getId());
                List<Task> newTasks = new LinkedList<Task>();
                Boolean unknownOrder = true;
                for(EvaluationStatus evaluationStatus : evaluationStatusWrapper.getEvaluationStatus()) {
                    for(Order order : ordersFromDatabase) {
                        if(order.getId().equals(evaluationStatus.getId())) {
                            unknownOrder = false;
                            break;
                        }
                    }
                }
                if(!unknownOrder) {
                    continue;
                }
                //newOrder.setOrderStatus(evaluationStatusWrapper.getEvaluationStatus().get(0).getStatus());
                ordersFromDatabase.add(newOrder);
            }
        }

        return ordersFromDatabase;
    }

    @Override
    public OrderResult getResult(UUID orderId) {
        ObjectMapper mapper = new ObjectMapper();
        OrderResult orderResult = null;
        
        try {
            orderResult = mapper.readValue(loadResult(orderId), OrderResult.class);
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
        mongoDatabaseAccess.insertJson(mongoDatabaseAccess.getOrdersCollection(), order.getJson());
    }
    
    // public void deleteOrder(Order order) {
    //     log.debug("Deleting Order: " + jsonFromOrder(order));
    //     ordersCollection.deleteOne(eq("_id", order.getId().toString()));
    // }
}
