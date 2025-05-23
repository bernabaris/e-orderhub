package com.github.bernabaris.inventoryservice.repository;

import com.github.bernabaris.inventoryservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByName(String name);
    boolean existsByName(String name);
}
