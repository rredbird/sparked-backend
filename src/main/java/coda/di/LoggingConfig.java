package coda.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import coda.shared.logging.Logging;
import coda.shared.logging.ILogging;

@Configuration
public class LoggingConfig {
    
    @Bean 
    public ILogging logging() {
        return new Logging();
    }
}

l