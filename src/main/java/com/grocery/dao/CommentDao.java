package com.grocery.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.model.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {

}
