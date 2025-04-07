package com.github.bernabaris.common.dto;


import com.github.bernabaris.common.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long userId;
    private Long productId;
    private Integer quantity;
    private OrderStatus status;

}
