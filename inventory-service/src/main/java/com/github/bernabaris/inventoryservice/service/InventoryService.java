package com.github.bernabaris.inventoryservice.service;

import com.github.bernabaris.common.model.Order;
import com.github.bernabaris.inventoryservice.entity.ProductEntity;
import com.github.bernabaris.inventoryservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryService {
    private final ProductRepository productRepository;

    @Autowired
    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @KafkaListener(topics = {"${spring.kafka.template.default-topic}"}, groupId = "inventory-service-group")
    public void updateInventory(Order order) {
        log.info("Received order: {}", order);

        ProductEntity product = productRepository.findById(order.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        int updatedStock = product.getStockQuantity() - order.getQuantity();
        if (updatedStock < 0) {
            throw new RuntimeException("Not enough stock available for product: " + product.getName());
        }
        product.setStockQuantity(updatedStock);
        productRepository.save(product);
        System.out.println("Inventory updated for product: " + product.getName());
    }
}
