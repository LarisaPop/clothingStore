package com.ecommerce.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{
    @Query("SELECT p FROM Product p WHERE p.name LIKE :n")
    List<Product> findByName(@Param("n") String name);
    
}
