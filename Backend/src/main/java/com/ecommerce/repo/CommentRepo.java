package com.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {

}
