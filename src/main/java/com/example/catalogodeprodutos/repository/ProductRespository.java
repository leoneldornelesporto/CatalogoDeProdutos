package com.example.catalogodeprodutos.repository;

import com.example.catalogodeprodutos.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRespository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByPriceBetween(Float value1, Float value2);
}
