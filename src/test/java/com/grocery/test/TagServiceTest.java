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

import com.grocery.dao.TagDao;
import com.grocery.model.Tag;
import com.grocery.service.TagService;

@SpringBootTest
public class TagServiceTest {

	@Autowired
	private TagService tagService;

	@MockBean
	private TagDao tagDao;

	void testAddTag()
	{
		Tag t1 = new Tag();
		t1.setId((long) 1);
		t1.setName("Fruits");
		Mockito.when(tagDao.save(t1)).thenReturn(t1);
		assertThat(tagService.addTag(t1)).isEqualTo(t1);
		
	}
	
	@Test
	void testGetTagById() throws Throwable {
		Tag t1 = new Tag();
		t1.setId((long) 1);
		t1.setName("Fruits");;
		Optional<Tag> p = Optional.of(t1);
		Mockito.when(tagDao.findById((long) 1)).thenReturn(p);
		assertThat(tagService.findTagById((long) 1)).isEqualTo(t1);
	}

	@Test
	void testGetAllTags() {
		Tag t1 = new Tag();
		t1.setId((long) 1);
		t1.setName("Fruits");
		
		Tag t2 = new Tag();
		t1.setId((long) 1);
		t1.setName("Fruits");

		List<Tag> addTag = new ArrayList<>();
		addTag.add(t1);
		addTag.add(t2);

		Mockito.when(tagDao.findAll()).thenReturn(addTag);
		assertThat(tagService.findAllTags()).isEqualTo(addTag);
	}
	
	@Test
	void testDeleteTag() 
	{
		Tag t1 = new Tag();
		t1.setId((long) 1);
		t1.setName("Fruits");
		
		Optional<Tag> t = Optional.of(t1);
		
		Mockito.when(tagDao.findById((long) 1)).thenReturn(t);
		Mockito.when(tagDao.existsById(t1.getId())).thenReturn(false);
		
		assertFalse(tagDao.existsById(t1.getId()));
	}	
}



