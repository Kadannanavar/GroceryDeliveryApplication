package com.grocery.service;

import java.util.List;

import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.Product;

public interface ProductService {

	Product addProductToCategory(Product product, long idCategory) throws ResourceNotFoundException;

	Product editProduct(Product product, long id) throws ResourceNotFoundException;

	Product findProductById(long id) throws ResourceNotFoundException;

	void deleteProduct(long id) throws ResourceNotFoundException;

	List<Product> findAllProducts() throws ResourceNotFoundException;

	List<Product> findProductsForCategory(long idCategory);

	Product getProduct(Long id);

}
