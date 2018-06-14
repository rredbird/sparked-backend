package coda.evaluationService;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.MongoClient;
import com.mongodb.annotations.Beta;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

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
}

