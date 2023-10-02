package com.ecommerce.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.repo.CategoryRepo;
import com.ecommerce.repo.ProductRepo;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

@Transactional
@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CategoryRepo categoryRepo;


	@Override
	public Product addProductToCategory(Product product, long idCategory) {
		Category category = categoryRepo.findById(idCategory).orElse(null);
		category.addProductToCategory(product);
		return productRepo.save(product);
	}



	@Override
	public Product editProduct(Product product, long id) {
		Product existProduct = productRepo.findById(id).orElse(null);
		existProduct.setName(product.getName());
		existProduct.setDescription(product.getDescription());
		existProduct.setPictureUrl(product.getPictureUrl());
		existProduct.setPrice(product.getPrice());
		return productRepo.save(existProduct);
	}

	@Override
	public Product findProductById(long id) {
		return productRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteProduct(long id) {
		productRepo.deleteById(id);
	}

	@Override
	public List<Product> findAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public List<Product> findProductsForCategory(long idCategory) {
		Category category = categoryRepo.findById(idCategory).orElse(null);
		return category.getProducts();
	}

	@Override
	public Product getProduct(Long id) {
		return productRepo.findById(id).orElse(null);
	}

}
