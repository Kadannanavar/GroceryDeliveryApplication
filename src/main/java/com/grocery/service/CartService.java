package com.grocery.service;

import java.util.List;

import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.Cart;

public interface CartService {
	
    Cart addCartToUser(Cart cart, long idUser);
	
	void deleteCart(long idCart) throws ResourceNotFoundException;
	
	List<Cart> findCartsForUser(long idUser);
	
	Cart findCartById(long Cartid) throws ResourceNotFoundException;
	
	void removeFromCart(long idCart, long idUser);
	
	Cart findByCartName(String name);

	Cart addCart(Cart cart)throws ResourceNotFoundException;


}
