package com.ecommerce.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Cart;

public interface CartRepo extends JpaRepository<Cart, Long>{
   Optional<Cart> findByName(String name);
}
