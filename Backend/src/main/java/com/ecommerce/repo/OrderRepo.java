package com.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
