package coda.configurationService;

import org.springframework.stereotype.Component;

import coda.model.order.ValidationMethods;
import coda.shared.dto.ClassifiersDto;
import coda.shared.dto.Datasets;
import coda.shared.dto.EvaluationMetrics;

@Component("configurationService")
public interface IConfigurationService {
    public ClassifiersDto getClassifiers();

    public ValidationMethods getValidationMethods();

    public EvaluationMetrics getEvaluationMetrics();

    public Datasets getDatasets();
}
