package com.grocery.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.Comment;
import com.grocery.service.CommentService;

@RestController
@RequestMapping(value = "/comment")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"},allowedHeaders = "*")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/findCommentById/{id}")

	public ResponseEntity<Comment> findCommentById(@PathVariable long id) throws ResourceNotFoundException {
		Comment c = commentService.findCommentById(id);
		return new ResponseEntity<Comment>(c, HttpStatus.OK);
	}

	@GetMapping("/findAllComments")
	public ResponseEntity<List<Comment>> findAllComments() throws ResourceNotFoundException {
		List<Comment> c = commentService.findAllComments();
		return ResponseEntity.ok(c);

	}

	@DeleteMapping("/deleteComment/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable("id") long id) throws ResourceNotFoundException {

		commentService.deleteComment(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/editComment/{id}")
	Comment editComment(@RequestBody Comment comment, @PathVariable long id) throws ResourceNotFoundException {
		return commentService.editComment(comment, id);
	}

	@PostMapping("/addCommentToProduct/{idProduct}")
	Comment addCommentToProduct(@RequestBody Comment comment, @PathVariable long idProduct) throws ResourceNotFoundException {
		return commentService.addCommentToProduct(comment, idProduct);

	}

	@GetMapping("/findCommentsForProduct/{idProduct}")
	List<Comment> findCommentsForProduct(@PathVariable long idProduct) {
		return commentService.findCommentsForProduct(idProduct);
	}

}
