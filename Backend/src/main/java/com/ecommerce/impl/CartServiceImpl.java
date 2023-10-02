package com.ecommerce.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.repo.CartRepo;
import com.ecommerce.repo.UserRepo;
import com.ecommerce.model.Cart;
import com.ecommerce.model.User;
import com.ecommerce.service.CartService;

@Transactional
@Component
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public Cart addCartToUser(Cart cart, long idUser) {
		User user = userRepo.findById(idUser).orElse(null);
		user.addCartToUser(cart);
		return cartRepo.save(cart);
	}

	@Override
	public void deleteCart(long id) {
		cartRepo.deleteById(id);
		
	}

	@Override
	public List<Cart> findCartsForUser(long idUser) {
		User user = userRepo.findById(idUser).orElse(null);
		return user.getCarts();
	}

	@Override
	public Cart findCartById(long id) {
		return cartRepo.findById(id).orElse(null);
	}

	@Override
	public void removeFromCart(long idCart, long idUser) {
		User user = userRepo.findById(idUser).orElse(null);
		Cart cart = cartRepo.findById(idCart).orElse(null);
		user.removeFromCart(cart);
		cartRepo.delete(cart);
	}

	@Override
	public Cart findByCartName(String name) {
		Optional<Cart> carts = cartRepo.findByName(name);
		if (carts.isPresent()) {
			Cart cart = carts.get();
			return cart;
		}
		return null;
	}

}
