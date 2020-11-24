package com.lemberski.demo.kafka.producer;

import com.lemberski.demo.kafka.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final KafkaTemplate<String, Notification> kafkaTemplate;

    @PostMapping
    private Mono<Notification> send(@RequestBody Notification notification) {
        sendToKafka(notification);
        return Mono.just(notification);
    }

    @Async
    void sendToKafka(Notification notification) {
        Message<Notification> message = MessageBuilder
                .withPayload(notification)
                .setHeader(KafkaHeaders.TOPIC, ProducerConfiguration.KAFKA_TOPIC)
                .build();

        kafkaTemplate.send(message);
    }

}
