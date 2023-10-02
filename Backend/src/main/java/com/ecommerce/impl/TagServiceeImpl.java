package com.ecommerce.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.repo.ProductRepo;
import com.ecommerce.repo.TagRepo;
import com.ecommerce.model.Product;
import com.ecommerce.model.Tag;
import com.ecommerce.service.TagService;

@Transactional
@Component
public class TagServiceeImpl implements TagService {
	
	@Autowired
	private TagRepo tagRepo;
	
	@Autowired
	private ProductRepo productRepo;

	@Override
	public void addTagToProduct(long idProduct, long idTag) {
		Product product = productRepo.findById(idProduct).orElse(null);
		Tag tag = tagRepo.findById(idTag).orElse(null);
		tag.addProductToTag(product);
		product.addTag(tag);
		
	}

	@Override
	public List<Tag> findTagsForProduct(long idProduct) {
		Product product = productRepo.findById(idProduct).orElse(null);
		return product.getTags();
	}

	@Override
	public void deleteTagFromProduct(long idTag, long idProduct) {
		Product product = productRepo.findById(idProduct).orElse(null);
		Tag tag = tagRepo.findById(idTag).orElse(null);
		product.getTags().remove(tag);
		
	}

	@Override
	public Tag addTag(Tag tag) {
		return tagRepo.save(tag);
	}

	@Override
	public void deleteTag(long id) {
        tagRepo.deleteById(id);
	}

	@Override
	public Tag findTagById(long id) {
		return tagRepo.findById(id).orElse(null);
	}

	@Override
	public List<Tag> findAllTags() {
		return tagRepo.findAll();
	}

	@Override
	public List<Product> findProductsForTag(long idTag) {
		Tag tag = tagRepo.findById(idTag).orElse(null);
		return tag.getProducts();
	}
	

}
