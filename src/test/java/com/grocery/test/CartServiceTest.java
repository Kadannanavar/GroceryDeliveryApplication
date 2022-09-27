package com.grocery.test;

import static org.assertj.core.api.Assertions.assertThat;



import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.grocery.dao.CartDao;
import com.grocery.model.Cart;
import com.grocery.service.CartService;

@SpringBootTest
public class CartServiceTest {

	@Autowired
	private CartService cartService;

	@MockBean
	private CartDao cartDao;

	void testAddCart()
	{
		Cart c1 = new Cart();
		c1.setId((long) 1);
		c1.setName("Apple");
		c1.setPrice(45.00);
		c1.setQuantity(1);
		c1.setPictureUrl("https://t4.ftcdn.net/jpg/00/59/96/75/360_F_59967553_9g2bvhTZf18zCmEVWcKigEoevGzFqXzq.jpg");
		Mockito.when(cartDao.save(c1)).thenReturn(c1);
		assertThat(cartService.addCart(c1)).isEqualTo(c1);
		
	}
	
	@Test
	void testGetCartById() throws Throwable {
		Cart c1 = new Cart();
		c1.setId((long) 1);
		c1.setName("Apple");
		c1.setPrice(45.00);
		c1.setPictureUrl("https://t4.ftcdn.net/jpg/00/59/96/75/360_F_59967553_9g2bvhTZf18zCmEVWcKigEoevGzFqXzq.jpg");
		c1.setQuantity(1);
		Optional<Cart> c = Optional.of(c1);
		Mockito.when(cartDao.findById((long) 1)).thenReturn(c);
		assertThat(cartService.findCartById((long) 1)).isEqualTo(c1);
	}
	
	@Test
	void testDeleteCart() 
	{
		Cart c1 = new Cart();
		c1.setId((long) 1);
		c1.setName("Apple");
		c1.setPrice(45.00);
		c1.setQuantity(1);
		c1.setPictureUrl("https://t4.ftcdn.net/jpg/00/59/96/75/360_F_59967553_9g2bvhTZf18zCmEVWcKigEoevGzFqXzq.jpg");
	
		Optional<Cart> c = Optional.of(c1);
		
		Mockito.when(cartDao.findById((long) 1)).thenReturn(c);
		Mockito.when(cartDao.existsById(c1.getId())).thenReturn(false);
		
		assertFalse(cartDao.existsById(c1.getId()));
	}	
}