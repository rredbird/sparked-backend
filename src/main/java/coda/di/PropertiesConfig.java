package coda.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import coda.shared.properties.Properties;

@Configuration
public class PropertiesConfig {
    private static Properties properties = new Properties();
    @Bean 
    public Properties properties() {
        return properties;
    }
}

