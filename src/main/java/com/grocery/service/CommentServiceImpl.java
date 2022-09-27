package com.grocery.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.grocery.dao.CommentDao;
import com.grocery.dao.ProductDao;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.Comment;
import com.grocery.model.Product;

@Service
@Transactional
@Component

public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private ProductDao productDao;

	@Override
	public Comment findCommentById(long id) throws ResourceNotFoundException {

		if (commentDao.findById(id).isPresent()) {
			Comment comment = commentDao.findById(id).get();
			return comment;
		} else {
			throw new ResourceNotFoundException("Comment with Id: " + id + " doesn't exist!!");
		}
	}

	@Override

	public List<Comment> findAllComments() throws ResourceNotFoundException {
		List<Comment> comment = (List<Comment>) commentDao.findAll();
		if (comment == null)
			throw new ResourceNotFoundException("Comment not created");
		return comment;
	}

	@Override
	public void deleteComment(long id) throws ResourceNotFoundException {

		if (commentDao.findById(id).isPresent()) {
			commentDao.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Comment with Id: " + id + " doesn't exist!!");
		}
	}

	@Override
	public Comment editComment(Comment comment, long id) {
		Comment existComponent = commentDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
		existComponent.setTitle(comment.getTitle());
		existComponent.setMessage(comment.getMessage());
		existComponent.setAddedAt(new Date());
		existComponent.setAddedBy(comment.getAddedBy());
		return commentDao.save(existComponent);
	}

	@Override
	public Comment addCommentToProduct(Comment comment, long idProduct) throws ResourceNotFoundException {

		return productDao.findById(idProduct).map(product -> {
			comment.setProduct(product);
			return commentDao.save(comment);
		}).orElseThrow(() -> new ResourceNotFoundException("Product not found"));

	}

	@Override
	public List<Comment> findCommentsForProduct(long idProduct) {
		Product product = productDao.findById(idProduct).orElse(null);
		return product.getComments();
	}

}
