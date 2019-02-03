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

import coda.shared.dto.ClassifiersDto;
import coda.shared.dto.Datasets;
import coda.shared.dto.EvaluationMetrics;
import coda.shared.dto.ValidationMethods;
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
            validationMethods = mapper.readValue(loadValidationMethodExample(), ValidationMethods.class);
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
            evaluationMetrics = mapper.readValue(loadEvaluationMetricsExample(), EvaluationMetrics.class);
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
            datasets = mapper.readValue(loadDatasetsExample(), Datasets.class);
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

    private String loadClassifiers() throws Exception {
        return apiCall("listClassifiers");
    }
    private String loadValidationMethodExample() {
        String validationMethodJson = null;
        try {
            validationMethodJson = new String(Files.readAllBytes(Paths.get("./resources/validationmethods.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return validationMethodJson;
    }
    private String loadEvaluationMetricsExample() {
        String evaluationMetricsJson = null;
        try {
            evaluationMetricsJson = new String(Files.readAllBytes(Paths.get("./resources/evaluationmetrics.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return evaluationMetricsJson;
    }
    private String loadDatasetsExample() {
        String datasetsJson = null;
        try {
            datasetsJson = new String(Files.readAllBytes(Paths.get("./resources/datasets.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datasetsJson;
    }

    public static String apiCall(String urlPath) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL("http://coda-api:5000/evaluation/" + urlPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
           result.append(line);
        }
        rd.close();
        return result.toString();
     }
}
