package com.lcwd.electronic_store.electronic_store.repository;

import com.lcwd.electronic_store.electronic_store.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductsRepo extends JpaRepository<Products,String> {
    public Optional<Products> findByProductName(String productName);
}

