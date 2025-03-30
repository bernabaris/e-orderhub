package com.github.bernabaris.orderservice.service;

import com.github.bernabaris.common.model.Order;
import com.github.bernabaris.orderservice.entity.OrderEntity;

import com.github.bernabaris.orderservice.repository.OrderRepository;
import com.github.bernabaris.orderservice.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        OrderEntity orderEntity = Converter.convertOrderToEntity(order);
        OrderEntity savedOrder = orderRepository.save(orderEntity);
        return Converter.convertEntityToOrder(savedOrder);
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
}
