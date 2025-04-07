package com.github.bernabaris.orderservice.controller;

import com.github.bernabaris.orderservice.config.KafkaProducer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/send/{topic}")
    public void sendMessageToKafka(@PathVariable String topic, @RequestBody String message) {
        kafkaProducer.sendMessage(topic,message);
    }
}
