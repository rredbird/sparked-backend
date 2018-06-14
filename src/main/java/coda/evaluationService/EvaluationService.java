package coda.evaluationService;

import java.util.Arrays;
import java.util.Date;
import java.io.IOException;
import java.io.File;

import java.nio.charset.Charset;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.MongoClient;
import com.mongodb.annotations.Beta;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import coda.shared.dto.Classifier;
import coda.shared.dto.Dataset;
import coda.shared.dto.EvaluationMetric;
import coda.shared.dto.ValidationMethod;
import coda.shared.properties.Properties;
import coda.shared.logging.Logging;

@Component("evaluationService")
public class EvaluationService {
    @Autowired
    private Properties properties;

    @Autowired 
    private Logging log;

    public Classifier[] getClassifiers() {
        log.debug(loadClassifierExample());
        return null;
    }

    public ValidationMethod[] getValidationMethods() {
        return null;
    }

    public EvaluationMetric[] getEvaluationMetrics() {
        return null;
    }

    public Dataset[] getDatasets() {
        return null;
    }

    private String loadClassifierExample() {
        String classifiersJson = null;
        try {
            // FIXME
            File file = ResourceUtils.getFile("classpath:resources/classifiers.json");
            
            if(file.exists()) {
                classifiersJson = new String(Files.readAllBytes(file.toPath()));
            } else {
                log.debug("File not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classifiersJson;
    }
}


