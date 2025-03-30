package com.github.bernabaris.orderservice.entity;

import com.github.bernabaris.common.model.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name= "orders")
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private Long userId;

    @Column(nullable=false)
    private Long productId;

    @Column(nullable=false)
    private Integer quantity;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable=false)
    private LocalDateTime orderDate;
}
