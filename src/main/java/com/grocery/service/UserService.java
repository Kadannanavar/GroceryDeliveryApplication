package com.grocery.service;

import java.util.List;

import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.User;

public interface UserService {
	
	  User addUser(User user);
	  
	  List<User> findAllUsers() throws ResourceNotFoundException;
	  
	  User editUser(User user, long id) throws ResourceNotFoundException;
	  
	  User findUserById(long id) throws ResourceNotFoundException;
	  
	  void deleteUser(long id) throws ResourceNotFoundException;
	  
	  User findByUsername(String username);

}
