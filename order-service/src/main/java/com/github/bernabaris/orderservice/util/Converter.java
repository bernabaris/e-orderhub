package com.github.bernabaris.orderservice.util;

import com.github.bernabaris.common.dto.OrderDto;
import com.github.bernabaris.common.model.Order;

import com.github.bernabaris.orderservice.entity.OrderEntity;


public class Converter {
    public static OrderEntity convertOrderDtoToEntity(OrderDto orderDto){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(orderDto.getUserId());
        orderEntity.setProductId(orderDto.getProductId());
        orderEntity.setQuantity(orderDto.getQuantity());
        orderEntity.setStatus(orderDto.getStatus());

        return orderEntity;
    }

    public static OrderDto convertOrderEntityToDto(OrderEntity orderEntity){
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(orderEntity.getUserId());
        orderDto.setProductId(orderEntity.getProductId());
        orderDto.setQuantity(orderEntity.getQuantity());
        orderDto.setStatus(orderEntity.getStatus());
        return orderDto;

    }

    public static Order convertEntityToOrder(OrderEntity orderEntity){
        Order order = new Order();
        order.setUserId(orderEntity.getUserId());
        order.setProductId(orderEntity.getProductId());
        order.setQuantity(orderEntity.getQuantity());
        order.setStatus(orderEntity.getStatus());
        order.setOrderDate(orderEntity.getOrderDate());
        return order;
    }

    public static OrderEntity convertOrderToEntity(Order order){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(order.getUserId());
        orderEntity.setProductId(order.getProductId());
        orderEntity.setQuantity(order.getQuantity());
        orderEntity.setStatus(order.getStatus());
        orderEntity.setOrderDate(order.getOrderDate());
        return orderEntity;
    }

    public static Order convertDtoToOrder(OrderDto orderDto){
        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        order.setProductId(orderDto.getProductId());
        order.setQuantity(orderDto.getQuantity());
        order.setStatus(orderDto.getStatus());
        return order;
    }
    public static OrderDto convertOrderToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(order.getUserId());
        orderDto.setProductId(order.getProductId());
        orderDto.setQuantity(order.getQuantity());
        orderDto.setStatus(order.getStatus());
        return orderDto;
    }
}
