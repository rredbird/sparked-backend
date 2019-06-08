package coda.configurationService;

import org.springframework.stereotype.Component;

import coda.model.order.Classifiers;
import coda.model.order.Datasets;
import coda.model.order.Metrics;
import coda.model.order.ValidationMethods;

@Component("configurationService")
public interface IConfigurationService {
    public Classifiers getClassifiers();

    public ValidationMethods getValidationMethods();

    public Metrics getEvaluationMetrics();

    public Datasets getDatasets();
}
