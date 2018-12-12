package coda.evaluationService;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component("evaluationService")
public interface IEvaluationService {
    public void RunTask(UUID taskId);

    /**
     * return values are 
     * Running
     * Finished
     * Error
     * more?
     */
    public String GetStatus(UUID taskId);

    public String GetOutput(UUID taskId);

    public void ClearOutput(UUID taskId);
}

