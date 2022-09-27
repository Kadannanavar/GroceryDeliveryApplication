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

import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.Cart;
import com.grocery.service.CartService;

@RestController
@RequestMapping(value = "/cart")

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"},allowedHeaders = "*")

public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping("/addCart")
	public ResponseEntity<?> addTag(@RequestBody Cart cart) throws ResourceNotFoundException {
		return ResponseEntity.ok(cartService.addCart(cart));
	}

	@GetMapping("/findCartById/{id}")
	public ResponseEntity<Cart> findCartById(@PathVariable long id) throws ResourceNotFoundException {
		Cart c = cartService.findCartById(id);
		return new ResponseEntity<Cart>(c, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteCart/{id}")
	public ResponseEntity<?> deleteCart(@PathVariable("id") long id) throws ResourceNotFoundException {
		cartService.deleteCart(id);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/addCartToUser/{idUser}")
	Cart addCartToUser(@RequestBody Cart cart, @PathVariable long idUser) {
		return cartService.addCartToUser(cart, idUser);
	}

	@GetMapping("/findCartsForUser/{idUser}")
	List<Cart> findCartsForUser(@PathVariable long idUser) {
		return cartService.findCartsForUser(idUser);
	}

	@DeleteMapping("/removeFromCart/{idCart}/{idUser}")
	void removeFromCart(@PathVariable long idCart, @PathVariable long idUser) {
		cartService.removeFromCart(idCart, idUser);
	}

	@GetMapping("/findByCartName/{name}")
	Cart findByCartName(@PathVariable String name) {
		return cartService.findByCartName(name);
	}

}
