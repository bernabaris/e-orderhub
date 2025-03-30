package com.github.bernabaris.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private OrderStatus status;
    private LocalDateTime orderDate;
}
