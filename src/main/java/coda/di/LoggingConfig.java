package coda.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import coda.shared.logging.Logging;

@Configuration
public class LoggingConfig {
    private static Logging logging = new Logging();
    @Bean 
    public Logging logging() {
        return logging;
    }
}

