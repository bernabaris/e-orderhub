package com.github.bernabaris.inventoryservice.util;

import com.github.bernabaris.common.model.Product;
import com.github.bernabaris.inventoryservice.entity.ProductEntity;

public class Converter {

    public static ProductEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .build();
    }
}
