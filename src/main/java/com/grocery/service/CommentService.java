package com.grocery.service;

import java.util.List;

import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.Comment;

public interface CommentService {

	Comment findCommentById(long id) throws ResourceNotFoundException;

	List<Comment> findAllComments() throws ResourceNotFoundException;

	Comment editComment(Comment comment, long id);

	void deleteComment(long id) throws ResourceNotFoundException;

	Comment addCommentToProduct(Comment comment, long idProduct) throws ResourceNotFoundException;

	List<Comment> findCommentsForProduct(long idProduct);

}
