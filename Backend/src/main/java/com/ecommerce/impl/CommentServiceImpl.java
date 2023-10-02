package com.ecommerce.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.repo.CommentRepo;
import com.ecommerce.repo.ProductRepo;
import com.ecommerce.model.Comment;
import com.ecommerce.model.Product;
import com.ecommerce.service.CommentService;

@Transactional
@Component
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ProductRepo productRepo;

	@Override
	public Comment addCommentToProduct(Comment comment, long idProduct) {
		Product product = productRepo.findById(idProduct).orElse(null);
		comment.setAddedAt(new Date());
		product.addCommentToProduct(comment);
		return commentRepo.save(comment);
	}

	@Override
	public Comment editComment(Comment comment, long id) {
		Comment existComponent = commentRepo.findById(id).orElse(null);
		existComponent.setTitle(comment.getTitle());
		existComponent.setMessage(comment.getMessage());
		existComponent.setAddedAt(new Date());
		existComponent.setAddedBy(comment.getAddedBy());
		return commentRepo.save(existComponent);
	}

	@Override
	public Comment findCommentById(long id) {
		return commentRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteComment(long id) {
		commentRepo.deleteById(id);
	}

	@Override
	public List<Comment> findCommentsForProduct(long idProduct) {
		Product product = productRepo.findById(idProduct).orElse(null);
		return product.getComments();
	}

	@Override
	public List<Comment> findAllComments() {
		return commentRepo.findAll();
	}
	

}
