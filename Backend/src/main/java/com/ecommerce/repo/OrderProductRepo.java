package com.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.OrderProduct;

public interface OrderProductRepo extends JpaRepository<OrderProduct, Long> {

}
