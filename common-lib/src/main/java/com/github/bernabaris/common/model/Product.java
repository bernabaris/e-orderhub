package com.github.bernabaris.common.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Long id;

    private String name;
    private BigDecimal price;

    private Integer stockQuantity;
}
