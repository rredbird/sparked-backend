package coda.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import coda.kafka.KafkaConnector;

@Configuration
public class KafkaConnectorConfig {
    @Bean
    public KafkaConnector kafkaConnector() {
        return new KafkaConnector();
    }
}
