package coda.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import coda.EvaluationService.EvaluationService;

@Configuration
public class EvaluationServiceConfig {
    @Bean
    public EvaluationService evaluationService() {
        return new EvaluationService(); 
    }
}


