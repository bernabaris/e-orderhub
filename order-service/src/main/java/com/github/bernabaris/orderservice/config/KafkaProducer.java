package com.github.bernabaris.orderservice.config;

import com.github.bernabaris.common.model.Order;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.gson = new Gson();
    }

    public void sendMessage(String topic, String message) {
        try {
            kafkaTemplate.send(topic, message);
            log.info("Message sent: {}", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
