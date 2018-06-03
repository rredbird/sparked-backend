package coda.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import coda.database.DataLayer;

@Configuration
public class DataLayerConfig {
    @Bean
    public DataLayer dataLayer() {
        return new DataLayer(); 
    }
}

