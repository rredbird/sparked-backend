package coda.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import coda.evaluationService.EvaluationService;

@Configuration
public class EvaluationServiceConfig {
    @Bean
    public EvaluationService evaluationService() {
        return new EvaluationService(); 
    }
}


