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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.dao.TagDao;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.Product;
import com.grocery.model.Tag;
import com.grocery.service.TagService;

@RestController
@RequestMapping(value = "/tag")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"},allowedHeaders = "*")
public class TagController {

	@Autowired
	private TagService tagService;

	@Autowired
	private TagDao tagDao;

	@PostMapping("/addTag")
	public ResponseEntity<?> addTag(@RequestBody Tag tag)throws ResourceNotFoundException {
		return ResponseEntity.ok(tagService.addTag(tag));
	}

	@GetMapping("/findTagById/{id}")
	public ResponseEntity<Tag> findTagById(@PathVariable long id) throws ResourceNotFoundException {
		Tag t = tagService.findTagById(id);
		return new ResponseEntity<Tag>(t, HttpStatus.OK);
	}

	@GetMapping("/findAllTags")
	public ResponseEntity<List<Tag>> findAllTags() throws ResourceNotFoundException {
		List<Tag> t = tagService.findAllTags();
		return ResponseEntity.ok(t);

	}

	@DeleteMapping("/deleteTag/{id}")

	public ResponseEntity<?> deleteTag(@PathVariable("id") long id) throws ResourceNotFoundException {

		tagService.deleteTag(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/addTagToProduct/{idProduct}/{idTag}")
	void addTagToProduct(@PathVariable long idProduct, @PathVariable long idTag) {
		tagService.addTagToProduct(idProduct, idTag);
	}

	@GetMapping("/findTagsForProduct/{idProduct}")
	List<Tag> findTagsForProduct(@PathVariable long idProduct) {
		return tagService.findTagsForProduct(idProduct);
	}

	@DeleteMapping("/deleteTagFromProduct")
	void deleteTagFromProduct(@PathVariable long idTag, @PathVariable long idProduct) {
		tagService.deleteTagFromProduct(idTag, idProduct);
	}

	@GetMapping("/findAllTagByName/{name}")
	List<Tag> findAllTagByName(@PathVariable String name) {
		return tagDao.findByName("%" + name + "%");
	}

	@GetMapping("/findProductsForTag/{idTag}")
	List<Product> findProductsForTag(@PathVariable long idTag) {
		return tagService.findProductsForTag(idTag);
	}

}
