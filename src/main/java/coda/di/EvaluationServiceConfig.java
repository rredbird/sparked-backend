package coda.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import coda.evaluationService.EvaluationService;
import coda.evaluationService.EvaluationServiceMock;
import coda.evaluationService.IEvaluationService;

@Configuration
public class EvaluationServiceConfig {
    @Bean
    public IEvaluationService evaluationService() {
        return new EvaluationServiceMock(); 
    }
}

