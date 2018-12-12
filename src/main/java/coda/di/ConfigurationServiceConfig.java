package coda.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import coda.configurationService.ConfigurationServiceMock;
import coda.configurationService.IConfigurationService;

@Configuration
public class ConfigurationServiceConfig {
    @Bean
    public IConfigurationService configurationService() {
        return new ConfigurationServiceMock(); 
    }
}

