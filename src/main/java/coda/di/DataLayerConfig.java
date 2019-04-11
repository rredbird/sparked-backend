package coda.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import coda.datalayer.MongoDatabaseAccess;

@Configuration
public class DataLayerConfig {
    @Bean
    public MongoDatabaseAccess mongoDatabaseAccess() {
        return new MongoDatabaseAccess(); 
    }
}

