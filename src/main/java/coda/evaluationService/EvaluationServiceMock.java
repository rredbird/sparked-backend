package coda.evaluationService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import coda.shared.dto.ClassifiersDto;
import coda.shared.dto.Datasets;
import coda.shared.dto.EvaluationMetrics;
import coda.shared.dto.ValidationMethods;
import coda.shared.properties.Properties;
import coda.shared.logging.ILogging;

public class EvaluationServiceMock implements IEvaluationService {
    @Autowired
    private ILogging log;
    
    public void RunTask(UUID taskId) {
    }

    public String GetStatus(UUID taskId) {
        return "Test Status...";
    }
 
    public String GetOutput(UUID taskId) {
        return "";
    }

    public void ClearOutput(UUID taskId) {
    }
}
