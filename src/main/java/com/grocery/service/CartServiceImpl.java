package com.grocery.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.grocery.dao.CartDao;
import com.grocery.dao.UserDao;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.Cart;
import com.grocery.model.User;

@Service
@Transactional
@Component

public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;

	@Autowired
	private UserDao userDao;

	@Override
	public Cart addCart(Cart cart) throws ResourceNotFoundException {
		return cartDao.save(cart);
	}
	
	@Override
	public Cart findCartById(long id) throws ResourceNotFoundException {
		if (cartDao.findById(id).isPresent()) {
			Cart cart = cartDao.findById(id).get();
			return cart;
		} else {
			throw new ResourceNotFoundException("Cart with Id: " + id + " doesn't exist!!");
		}
	}

	@Override

	public void deleteCart(long id) throws ResourceNotFoundException {
		if (cartDao.findById(id).isPresent()) {
			cartDao.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Cart with Id: " + id + " doesn't exist!!");
		}
	}

	@Override
	public Cart addCartToUser(Cart cart, long idUser) {
		User user = userDao.findById(idUser).orElse(null);
		user.addCartToUser(cart);
		return cartDao.save(cart);
	}

	@Override
	public List<Cart> findCartsForUser(long idUser) {
		User user = userDao.findById(idUser).orElse(null);
		return user.getCarts();

	}

	@Override
	public void removeFromCart(long idCart, long idUser) {
		User user = userDao.findById(idUser).orElse(null);
		Cart cart = cartDao.findById(idCart).orElse(null);
		user.removeFromCart(cart);
		cartDao.delete(cart);
	}

	@Override
	public Cart findByCartName(String name) {
		Optional<Cart> carts = cartDao.findByName(name);
		if (carts.isPresent()) {
			Cart cart = carts.get();
			return cart;
		}
		return null;
	}

	
}
