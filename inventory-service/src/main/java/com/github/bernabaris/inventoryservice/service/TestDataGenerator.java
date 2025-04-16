package com.github.bernabaris.inventoryservice.service;

import com.github.bernabaris.common.model.Product;
import com.github.bernabaris.inventoryservice.entity.ProductEntity;
import com.github.bernabaris.inventoryservice.repository.ProductRepository;
import com.github.bernabaris.inventoryservice.util.Converter;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@ConditionalOnProperty(name = "generate.test.data", havingValue = "true")
@Slf4j
public class TestDataGenerator {

    private final ProductRepository productRepository;

    public TestDataGenerator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void generateTestData() {
        if (!productRepository.existsByName("Test Product 202")) {
            ProductEntity productEntity = ProductEntity.builder()
                    .name("Test Product 202")
                    .price(new BigDecimal("1000.0"))
                    .stockQuantity(100)
                    .build();
            productRepository.saveAndFlush(productEntity);
            log.info("✅ Test product inserted.");
        } else {
            log.info("ℹ️ Test product already exists.");
        }
    }

}
