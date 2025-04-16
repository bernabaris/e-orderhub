package com.github.bernabaris.orderservice.controller;

import com.github.bernabaris.common.dto.OrderDto;
import com.github.bernabaris.common.model.Order;


import com.github.bernabaris.orderservice.service.OrderService;
import com.github.bernabaris.orderservice.util.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        Order order = Converter.convertDtoToOrder(orderDto);
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(Converter.convertOrderToDto(createdOrder));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersByUserId(@PathVariable Long userId) {
        List<OrderDto> orders = orderService.getOrdersByUserId(userId).stream()
                .map(Converter::convertOrderToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders().stream()
                .map(Converter::convertOrderToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        try {
            Order order = orderService.getOrderById(id);
            OrderDto orderDto = Converter.convertOrderToDto(order);
            return ResponseEntity.ok(orderDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
