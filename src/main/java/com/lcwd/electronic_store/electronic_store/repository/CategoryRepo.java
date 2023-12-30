package com.lcwd.electronic_store.electronic_store.repository;

import com.lcwd.electronic_store.electronic_store.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,String> {
}
