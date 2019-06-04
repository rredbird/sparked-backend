package coda.configurationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.beans.factory.annotation.Autowired;

import coda.model.order.Datasets;
import coda.model.order.EvaluationMetrics;
import coda.model.order.ValidationMethods;
import coda.shared.dto.ClassifiersDto;
import coda.shared.properties.Properties;
import coda.shared.logging.ILogging;

public class ConfigurationService implements IConfigurationService {
    @Autowired
    private Properties properties;

    @Autowired
    private ILogging log;

    public ClassifiersDto getClassifiers() {
        ClassifiersDto classifiers = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            classifiers = mapper.readValue(loadClassifiers(), ClassifiersDto.class);
        } catch (JsonGenerationException e) {
            log.exception(e);
        } catch (JsonMappingException e) {
            log.exception(e);
        } catch (IOException e) {
            log.exception(e);
        } catch (Exception e) {
            log.exception(e);
        }

        log.trace(classifiers.getClassifiers().size() + " classifiers found.");

        return classifiers;
    }

    public ValidationMethods getValidationMethods() {
        ValidationMethods validationMethods = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            validationMethods = mapper.readValue(loadValidationMethod(), ValidationMethods.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.trace(validationMethods.getValidators().size() + " validation methods found.");

        return validationMethods;
    }

    public EvaluationMetrics getEvaluationMetrics() {
        EvaluationMetrics evaluationMetrics = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            evaluationMetrics = mapper.readValue(loadEvaluationMetrics(), EvaluationMetrics.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.trace(evaluationMetrics.getMetrics().size() + " evaluation metrics found.");

        return evaluationMetrics;
    }

    public Datasets getDatasets() {
        Datasets datasets = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            datasets = mapper.readValue(loadDatasets(), Datasets.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
             e.printStackTrace();       
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.trace(datasets.getDatasets().size() + " datasets found.");

        return datasets;
    }

    private String loadClassifiers() {
        String classifiersJson = "";
        try { 
            classifiersJson = apiCall("listClassifiers");
        } catch (Exception e) {
            log.exception(e);
            log.error("Classifiers could not be loaded. Loading local defaults instead.");
            classifiersJson = fileCall("classifiers.json");
        }

        return classifiersJson;

    }
    private String loadValidationMethod() {
        String validationMethodJson = null;
        try { 
            validationMethodJson = apiCall("listValidationMethods");
        } catch (Exception e) {
            log.exception(e);
            log.error("Validation methods could not be loaded. Loading local defaults instead.");
            validationMethodJson = fileCall("validationmethods.json");
        }
        return validationMethodJson;
    }
    private String loadEvaluationMetrics() {
        String evaluationMetricsJson = null;
        try { 
            evaluationMetricsJson = apiCall("listEvaluationMetrics");
        } catch (Exception e) {
            log.exception(e);
            log.error("Validation methods could not be loaded. Loading local defaults instead.");
            evaluationMetricsJson = fileCall("evaluationmetrics.json");
        }
        return evaluationMetricsJson;
    }
    private String loadDatasets() {
        String datasetsJson = null;
        try { 
            datasetsJson = apiCall("listDatasets?forcedRefresh=false");
        } catch (Exception e) {
            log.exception(e);
            log.error("Validation methods could not be loaded. Loading local defaults instead.");
            datasetsJson = fileCall("datasets.json");
        }
        return datasetsJson;
    }

    public String fileCall(String urlPath) {
        String fileData = "";
        try {
            fileData = new String(Files.readAllBytes(Paths.get("./resources/" + urlPath)));
        } catch (Exception e) {
            log.exception(e);
        }
        return fileData;
    }

    public static String apiCall(String urlPath) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL("http://10.0.2.55:5000/evaluation/" + urlPath);
        HttpURLConnection.setFollowRedirects(false);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(1000);
        connection.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
           result.append(line);
        }
        rd.close();
        return result.toString();
     }
}
