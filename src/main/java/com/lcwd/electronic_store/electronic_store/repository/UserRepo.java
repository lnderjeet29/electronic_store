package com.lcwd.electronic_store.electronic_store.repository;

import com.lcwd.electronic_store.electronic_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    public Optional<User> findByEmail(String email);
}
