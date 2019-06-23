package coda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import coda.datalayer.MongoDatabaseAccess;
import coda.model.order.Classifier;
import coda.model.order.Dataset;
import coda.model.order.Metric;
import coda.model.order.ValidationMethod;
import coda.configurationService.IConfigurationService;
import coda.shared.logging.ILogging;
import coda.shared.properties.Properties;

@RestController
@Component
@CrossOrigin(origins = Properties.CorsOriginAdress)
public class ConfigurationController {
    @Autowired
    private MongoDatabaseAccess dataLayer;

    @Autowired
    private IConfigurationService configurationService;

    @Autowired
    private ILogging log;

    public ConfigurationController() {
    }

    @GetMapping("/classifiers")
    public List<Classifier> classifiers() {
        return configurationService.getClassifiers().getClassifiers();
    }

    @GetMapping("/validationmethods")
    public List<ValidationMethod> validationMethods() {
        return configurationService.getValidationMethods().getValidators();
    }

    @GetMapping("/evaluationmetrics")
    public List<Metric> evaluationMetrics() {
        return configurationService.getEvaluationMetrics().getMetrics();
    }

    @GetMapping("/datasets")
    public List<Dataset> datasets() {
        return configurationService.getDatasets().getDatasets();
    }
}
