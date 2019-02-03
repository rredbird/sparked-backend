package coda.configurationService;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Date;

import java.io.IOException;
import java.io.File;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.bson.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import coda.shared.dto.ClassifiersDto;
import coda.shared.dto.Datasets;
import coda.shared.dto.EvaluationMetrics;
import coda.shared.dto.ValidationMethods;
import coda.shared.properties.Properties;
import coda.shared.logging.ILogging;

public class ConfigurationServiceMock implements IConfigurationService {
    @Autowired 
    private ILogging log;

    public ClassifiersDto getClassifiers() {
        ClassifiersDto classifiers = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            classifiers = mapper.readValue(loadClassifierExample(), ClassifiersDto.class);
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
        String classifiersJson = null;
        try {
            classifiersJson = new String(Files.readAllBytes(Paths.get("./resources/classifiers.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classifiersJson;
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
}

