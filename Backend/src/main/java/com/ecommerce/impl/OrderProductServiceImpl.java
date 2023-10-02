package com.ecommerce.impl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.repo.OrderProductRepo;
import com.ecommerce.model.OrderProduct;
import com.ecommerce.service.OrderProductService;

@Transactional
@Component
public class OrderProductServiceImpl implements OrderProductService {
	
	@Autowired
	private OrderProductRepo orderProductRepo;

	@Override
	public OrderProduct create(
			@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct) {
		return this.orderProductRepo.save(orderProduct);
	}

}
