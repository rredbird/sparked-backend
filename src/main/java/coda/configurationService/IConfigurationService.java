package coda.configurationService;

import org.springframework.stereotype.Component;

import coda.model.order.Datasets;
import coda.model.order.EvaluationMetrics;
import coda.model.order.ValidationMethods;
import coda.shared.dto.ClassifiersDto;

@Component("configurationService")
public interface IConfigurationService {
    public ClassifiersDto getClassifiers();

    public ValidationMethods getValidationMethods();

    public EvaluationMetrics getEvaluationMetrics();

    public Datasets getDatasets();
}
