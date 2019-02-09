package coda.configurationService;

import org.springframework.stereotype.Component;

import coda.shared.dto.ClassifiersDto;
import coda.shared.dto.Datasets;
import coda.shared.dto.EvaluationMetrics;
import coda.shared.dto.ValidationMethods;

@Component("configurationService")
public interface IConfigurationService {
    public ClassifiersDto getClassifiers();

    public ValidationMethods getValidationMethods();

    public EvaluationMetrics getEvaluationMetrics();

    public Datasets getDatasets();
}
