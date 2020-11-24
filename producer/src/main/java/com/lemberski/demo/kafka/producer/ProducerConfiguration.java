package com.lemberski.demo.kafka.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class ProducerConfiguration {

    static final String KAFKA_TOPIC = "notification";

    @Bean
    public NewTopic topic() {
        return new NewTopic(KAFKA_TOPIC, 1, (short) 1);
    }

}
