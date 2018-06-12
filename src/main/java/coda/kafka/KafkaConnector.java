package coda.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.Exception;

import coda.shared.logging.Logging;



@Component("kafkaConnector")
public class KafkaConnector {
    @Autowired 
    private Logging log;

    @Autowired 
    private coda.shared.properties.Properties properties;

    private KafkaProducer<Long, String> producer;

    public KafkaConnector() {
        System.out.println("KafkaConnector constructor");
    }

    public void initialize() {
        this.producer = createProducer();
    }

    private KafkaProducer<Long, String> createProducer() {
            Properties props = new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:2181");
            props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    
            return new KafkaProducer<>(props);
    }

    public void runProducerTestmessage() {
        try {
            long index = 0;
            final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>("CodaTestTopic", index, "Hello Mom " + index);

            RecordMetadata metadata = producer.send(record).get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.flush();
            producer.close();
        }
    }
}
