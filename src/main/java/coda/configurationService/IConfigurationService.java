package coda.configurationService;

import org.bson.Document;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import coda.shared.dto.Classifiers;
import coda.shared.dto.Datasets;
import coda.shared.dto.EvaluationMetrics;
import coda.shared.dto.ValidationMethods;

@Component("configurationService")
public interface IConfigurationService {
    public Classifiers getClassifiers();

    public ValidationMethods getValidationMethods();

    public EvaluationMetrics getEvaluationMetrics();

    public Datasets getDatasets();
}
