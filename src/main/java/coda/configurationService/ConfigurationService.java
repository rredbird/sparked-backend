package coda.configurationService;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.beans.factory.annotation.Autowired;

import coda.shared.dto.Classifiers;
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

    public Classifiers getClassifiers() {
        Classifiers classifiers = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            classifiers = mapper.readValue(loadClassifierExample(), Classifiers.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
             e.printStackTrace();       
        } catch (IOException e) {
            e.printStackTrace();
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

    private String loadClassifierExample() {
        return "";
    }
    private String loadValidationMethodExample() {
        return "";
    }
    private String loadEvaluationMetricsExample() {
        return "";
    }
    private String loadDatasetsExample() {
        return "";
    }
}
