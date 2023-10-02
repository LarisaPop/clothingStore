package com.ecommerce.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.repo.CategoryRepo;
import com.ecommerce.repo.UserRepo;
import com.ecommerce.model.Category;
import com.ecommerce.model.User;
import com.ecommerce.service.CategoryService;

@Transactional
@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public Category addCategoryToUser(Category category, long idUser) {
		User user = userRepo.findById(idUser).orElse(null);
		user.addCategoryToUser(category);
		return categoryRepo.save(category);
	}

	@Override
	public Category editCategory(Category category, long id) {
		Category existsCategory = categoryRepo.findById(id).orElse(null);
		existsCategory.setName(category.getName());
		return categoryRepo.save(existsCategory);
	}

	@Override
	public Category findCategoryById(long id) {
		return categoryRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteCategory(long id) {
		categoryRepo.deleteById(id);
	}

	@Override
	public List<Category> findAllCategories() {
		return categoryRepo.findAll();
	}

	@Override
	public List<Category> findCategoriesForUser(long id) {
		User user = userRepo.findById(id).orElse(null);
		return user.getCategories();
	}

}
