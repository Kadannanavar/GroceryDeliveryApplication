package com.grocery.test;

import static org.assertj.core.api.Assertions.assertThat;



import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.grocery.dao.CommentDao;
import com.grocery.model.Comment;
import com.grocery.service.CommentService;


@SpringBootTest
public class CommentServiceTest {

	@Autowired
	private CommentService commentService;

	@MockBean
	private CommentDao commentDao;

	@Test
	void testGetCommentById() throws Throwable {
		Comment c1 = new Comment();
		c1.setId((long) 1);
		c1.setTitle("About Fruits");
		c1.setMessage("Fresh");
		c1.setAddedBy("Jack");
		//c1.setAddedAt();
		Optional<Comment> c = Optional.of(c1);
		Mockito.when(commentDao.findById((long) 1)).thenReturn(c);
		assertThat(commentService.findCommentById((long) 1)).isEqualTo(c1);
	}

	@Test
	void testGetAllComments() {
		Comment c1 = new Comment();
		c1.setId((long) 1);
		c1.setTitle("About Fruits");
		c1.setTitle("About Fruits");
		c1.setMessage("Fresh");
		c1.setAddedBy("Jack");
		//c1.setAddedAt();
		
		Comment c2 = new Comment();
		c2.setId((long) 1);
		c2.setTitle("About Fruits");
		c2.setMessage("Fresh");
		c2.setAddedBy("Jack");
		//c2.setAddedAt();
		List<Comment> addComment = new ArrayList<>();
		addComment.add(c1);
		addComment.add(c2);
		

		Mockito.when(commentDao.findAll()).thenReturn(addComment);
		assertThat(commentService.findAllComments()).isEqualTo(addComment);
	}
	
	@Test
	void testDeleteComment() 
	{
		Comment c1 = new Comment();
		c1.setId((long) 1);
		c1.setTitle("About Fruits");
		c1.setMessage("Fresh");
		c1.setAddedBy("Jack");
		//c1.setAddedAt();
		
		Optional<Comment> c = Optional.of(c1);
		
		Mockito.when(commentDao.findById((long) 1)).thenReturn(c);
		Mockito.when(commentDao.existsById(c1.getId())).thenReturn(false);
		
		assertFalse(commentDao.existsById(c1.getId()));
	}	
}




