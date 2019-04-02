package com.stackroute.kafkaConfiguration;

import com.stackroute.domain.SearchDocument;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


//This configuration class will specify configuration for producer

@Configuration
public class ProducerConfiguration {
    @Bean
    public ProducerFactory<String, SearchDocument> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }


    // Creating bean of kafka template
    @Bean
    public KafkaTemplate<String, SearchDocument> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}