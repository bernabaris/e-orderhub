package com.github.bernabaris.inventoryservice.service;

import com.github.bernabaris.common.model.Order;
import com.github.bernabaris.common.util.GsonUtil;
import com.github.bernabaris.inventoryservice.entity.ProductEntity;
import com.github.bernabaris.inventoryservice.repository.ProductRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class InventoryService {

    private final ProductRepository productRepository;
    private final Gson gson = GsonUtil.getGson();

    @Autowired
    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.order}", groupId = "inventory-service-group")
    public void updateInventory(String message) {
        try {
            log.info("Received order: {}", message);
            Order order = gson.fromJson(message, Order.class);
            log.info("Product ID in order: {}", order.getProductId());

            ProductEntity product = productRepository.findById(order.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            int updatedStock = product.getStockQuantity() - order.getQuantity();
            if (updatedStock < 0) {
                throw new RuntimeException("Not enough stock available for product: " + product.getName());
            }
            product.setStockQuantity(updatedStock);
            productRepository.save(product);
            log.info("Inventory updated for product: {}", product.getName());

        } catch (Exception e) {
            log.error("Error processing order message: {}", message, e);
        }
    }

    public ProductEntity addProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    public Optional<ProductEntity> getProductById(Long productId) {
        return productRepository.findById(productId);
    }
}
