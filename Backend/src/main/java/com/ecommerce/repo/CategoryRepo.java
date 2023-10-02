package com.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>  {

}
