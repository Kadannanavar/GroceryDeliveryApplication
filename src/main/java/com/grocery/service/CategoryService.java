package com.grocery.service;

import java.util.List;

import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.Category;

public interface CategoryService {
	
	
	Category addCategoryToUser(Category category, long idUser) throws ResourceNotFoundException;
	
	Category editCategory(Category category, long id) throws ResourceNotFoundException;
	
	Category findCategoryById(long id) throws ResourceNotFoundException;
	
	void deleteCategory(long id) throws ResourceNotFoundException ;
	
	List<Category> findAllCategories() throws ResourceNotFoundException;
	
	List<Category> findCategoriesForUser(long id);

}
