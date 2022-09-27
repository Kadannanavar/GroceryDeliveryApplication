package com.grocery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grocery.dao.CategoryDao;
import com.grocery.dao.ProductDao;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.Category;
import com.grocery.model.Product;

@Service
@Transactional
@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public Product findProductById(long id) throws ResourceNotFoundException {

		if (productDao.findById(id).isPresent()) {
			Product product = productDao.findById(id).get();
			return product;
		} else {
			throw new ResourceNotFoundException("Product with Id: " + id + " doesn't exist!!");
		}
	}

	@Override
	public List<Product> findAllProducts() throws ResourceNotFoundException {
		List<Product> product = (List<Product>) productDao.findAll();
		if (product == null)
			throw new ResourceNotFoundException("Product not created");
		return product;
	}

	@Override
	public void deleteProduct(long id) throws ResourceNotFoundException {

		if (productDao.findById(id).isPresent()) {
			productDao.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Product with Id: " + id + " doesn't exist!!");
		}
	}

	@Override
	public Product editProduct(Product product, long id) {
		Product existProduct = productDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
		existProduct.setName(product.getName());
		existProduct.setDescription(product.getDescription());
		existProduct.setPictureUrl(product.getPictureUrl());
		existProduct.setPrice(product.getPrice());
		return productDao.save(existProduct);

	}

	@Override
	public Product addProductToCategory(Product product, long idCategory) throws ResourceNotFoundException {

		return categoryDao.findById(idCategory).map(category -> {
			product.setCategory(category);
			return productDao.save(product);
		}).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

	}

	@Override
	public List<Product> findProductsForCategory(long idCategory) {
		Category category = categoryDao.findById(idCategory).orElse(null);
		return category.getProducts();
	}

	@Override
	public Product getProduct(Long id) {
		return productDao.findById(id).orElse(null);
	}

}
