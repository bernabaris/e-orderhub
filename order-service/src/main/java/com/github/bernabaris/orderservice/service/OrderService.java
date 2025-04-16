package com.github.bernabaris.orderservice.service;

import com.github.bernabaris.common.model.Order;
import com.github.bernabaris.common.util.GsonUtil;
import com.github.bernabaris.orderservice.config.KafkaProducer;
import com.github.bernabaris.orderservice.entity.OrderEntity;

import com.github.bernabaris.orderservice.repository.OrderRepository;
import com.github.bernabaris.orderservice.util.Converter;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private KafkaProducer kafkaProducer;
    private final Gson gson = GsonUtil.getGson();

    @Value("${spring.kafka.topic.order}")
    private String orderTopic;

    @Autowired
    public OrderService(OrderRepository orderRepository, KafkaProducer kafkaProducer) {
        this.orderRepository = orderRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public Order createOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        OrderEntity orderEntity = Converter.convertOrderToEntity(order);
        OrderEntity savedOrder = orderRepository.save(orderEntity);
        order = Converter.convertEntityToOrder(savedOrder);
        String orderJson = gson.toJson(order);
        kafkaProducer.sendMessage(orderTopic, orderJson);
        return order;
    }

    public List<Order> getOrdersByUserId(Long userId) {
        List<OrderEntity> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(Converter::convertEntityToOrder)
                .collect(Collectors.toList());
    }

    public List<Order> getAllOrders() {
        List<OrderEntity> orders = orderRepository.findAll();
        return orders.stream()
                .map(Converter::convertEntityToOrder)
                .collect(Collectors.toList());
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(Converter::convertEntityToOrder)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));
    }

}
