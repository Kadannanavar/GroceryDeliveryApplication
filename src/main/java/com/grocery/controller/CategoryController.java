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
import com.grocery.model.Category;
import com.grocery.service.CategoryService;

@RestController
@RequestMapping(value = "/category")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"},allowedHeaders = "*")

public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/findCategoryById/{id}")
	public ResponseEntity<Category> findCategoryById(@PathVariable long id) throws ResourceNotFoundException {
		Category c = categoryService.findCategoryById(id);
		return new ResponseEntity<Category>(c, HttpStatus.OK);
	}

	@DeleteMapping("/deleteCategory/{id}")

	public ResponseEntity<?> deleteCategory(@PathVariable("id") long id) throws ResourceNotFoundException {

		categoryService.deleteCategory(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/findAllCategories")

	public ResponseEntity<List<Category>> findAllCategories() throws ResourceNotFoundException {
		List<Category> c = categoryService.findAllCategories();
		return ResponseEntity.ok(c);

	}

	@PutMapping("/editCategory/{id}")
	Category editCategory(@RequestBody Category category, @PathVariable long id) throws ResourceNotFoundException {
		return categoryService.editCategory(category, id);
	}

	@PostMapping("/addCategoryToUser/{idUser}")
	Category addCategoryToUser(@RequestBody Category category, @PathVariable long idUser)
			throws ResourceNotFoundException {
		{
			return categoryService.addCategoryToUser(category, idUser);
		}
	}

	@GetMapping("/findCategoriesForUser/{id}")
	List<Category> findCategoriesForUser(@PathVariable long id) {
		return categoryService.findCategoriesForUser(id);
	}

}
