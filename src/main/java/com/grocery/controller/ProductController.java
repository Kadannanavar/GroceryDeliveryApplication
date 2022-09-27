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

import com.grocery.dao.ProductDao;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.Product;
import com.grocery.service.ProductService;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"},allowedHeaders = "*")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductDao productDao;

	@GetMapping("/findProductById/{id}")
	public ResponseEntity<Product> findProductById(@PathVariable long id) throws ResourceNotFoundException {
		Product p = productService.findProductById(id);
		return new ResponseEntity<Product>(p, HttpStatus.OK);
	}

	@GetMapping("/findAllProducts")
	public ResponseEntity<List<Product>> findAllProducts() throws ResourceNotFoundException {
		List<Product> p = productService.findAllProducts();
		return ResponseEntity.ok(p);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) throws ResourceNotFoundException {

		productService.deleteProduct(id);
		return ResponseEntity.ok().build();
	}

//	@PutMapping("/editProduct/{id}")
//	Product editProduct(@RequestBody Product product, @PathVariable long id) throws ResourceNotFoundException {
//		return productService.editProduct(product, id);
//	}
	
	@PutMapping("/editProduct/{id}")

	public ResponseEntity<Product> editProduct(@RequestBody Product product, @PathVariable long id) throws ResourceNotFoundException {

		return ResponseEntity.ok(productService.editProduct(product, id));
	}
	
	
	@PostMapping("/addProductToCategory/{idCategory}")
	Product addProductToCategory(@RequestBody Product product, @PathVariable long idCategory)
			throws ResourceNotFoundException {

		{
			return productService.addProductToCategory(product, idCategory);
		}
	}

	@GetMapping("/findProductsForCategory/{idCategory}")
	List<Product> findProductsForCategory(@PathVariable long idCategory) {
		return productService.findProductsForCategory(idCategory);
	}

	@GetMapping("/findByName/{name}")
	List<Product> findByName(@PathVariable String name) {
		return productDao.findByName("%" + name + "%");
	}

}
