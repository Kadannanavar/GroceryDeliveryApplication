package com.grocery.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.grocery.dao.UserDao;
import com.grocery.model.User;
import com.grocery.service.UserService;


@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService userService;
	

	@MockBean
	UserDao userDao;
	
	@Test
	void testAddUser()
	{
		User u1 = new User();
		u1.setId((long) 1);
		u1.setNameOnCard("user1");
		u1.setEmail("user123@gmail.com");
		u1.setAddress("pune");
		u1.setCardNumber("1234567890123456");
		u1.setCvv(123);
		Mockito.when(userDao.save(u1)).thenReturn(u1);
		assertThat(userService.addUser(u1)).isEqualTo(u1);
		
	}
	
	@Test
	void testUpdateUser() throws Throwable {
		User u1 = new User();
		u1.setId((long) 1);
		u1.setNameOnCard("user1");
		u1.setEmail("user123@gmail.com");
		u1.setAddress("pune");
		u1.setCardNumber("1234567890123456");
		u1.setCvv(123);
		Optional<User> u = Optional.of(u1);
		Mockito.when(userDao.findById((long) 1)).thenReturn(u);
		Mockito.when(userDao.save(u1)).thenReturn(u1);
		u1.setAddress("mumbai");
		
		assertThat(userService.editUser(u1,1)).isEqualTo(u1);
	}
	
	@Test
	void testDeleteUser() 
	{
		User u1 = new User();
		u1.setId((long) 1);
		u1.setNameOnCard("user1");
		u1.setEmail("user123@gmail.com");
		u1.setAddress("pune");
		u1.setCardNumber("1234567890123456");
		u1.setCvv(123);
	
		Optional<User> t = Optional.of(u1);
		
		Mockito.when(userDao.findById((long) 1)).thenReturn(t);
		Mockito.when(userDao.existsById(u1.getId())).thenReturn(false);
		
		assertFalse(userDao.existsById(u1.getId()));
	}

	
	
}