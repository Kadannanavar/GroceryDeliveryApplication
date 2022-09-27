package com.grocery.service;

import java.util.List;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.grocery.dao.CategoryDao;
import com.grocery.dao.UserDao;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.Category;
import com.grocery.model.User;

@Service
@Transactional
@Component

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private UserDao userDao;

	@Override

	public Category findCategoryById(long id) throws ResourceNotFoundException {

		if (categoryDao.findById(id).isPresent()) {
			Category category = categoryDao.findById(id).get();
			return category;
		} else {
			throw new ResourceNotFoundException("Category with Id: " + id + " doesn't exist!!");
		}
	}

	@Override

	public void deleteCategory(long id) throws ResourceNotFoundException {

		if (categoryDao.findById(id).isPresent()) {
			categoryDao.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Category with Id: " + id + " doesn't exist!!");
		}
	}

	@Override

	public List<Category> findAllCategories() throws ResourceNotFoundException {
		List<Category> category = (List<Category>) categoryDao.findAll();
		if (category == null)
			throw new ResourceNotFoundException("Category not created");
		return category;
	}

	@Override
	public Category editCategory(Category category, long id) {
		Category existsCategory = categoryDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		existsCategory.setName(category.getName());
		return categoryDao.save(existsCategory);
	}

	@Override

	public Category addCategoryToUser(Category category, long idUser) throws ResourceNotFoundException {

		return userDao.findById(idUser).map(user -> {
			category.setUser(user);
			return categoryDao.save(category);
		}).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	@Override
	public List<Category> findCategoriesForUser(long id) {
		User user = userDao.findById(id).orElse(null);
		return user.getCategories();
	}

}
