package com.ecommerce.impl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.repo.OrderRepo;
import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	private OrderRepo orderRepo;
	public OrderServiceImpl(OrderRepo orderRepo) {
		this.orderRepo = orderRepo;
	}

	@Override
	public @NotNull Iterable<Order> getAllOrders() {
		return this.orderRepo.findAll();
	}

	@Override
	public Order create(@NotNull(message = "The order cannot be null.") @Valid Order order) {
		return this.orderRepo.save(order);
	}

	@Override
	public void update(@NotNull(message = "The order cannot be null.") @Valid Order order) {
		this.orderRepo.save(order);
		
	}

}
