package com.grocery.service;

import java.util.List;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.grocery.dao.UserDao;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.User;

@Service
@Transactional
@Component

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;


	@Override
	public User addUser(User user) {
		List<User> users = userDao.findAll();
		if (users.size() == 0) {
			user.setAdmin(true);
		}
		
		for (User userExist : users) {
			if (user.getUsername().equals(userExist.getUsername())) {
				userExist.setUsername(userExist.getUsername());
				userExist.setPassword(userExist.getPassword());
				return userDao.save(userExist);
			}
		}
	
		return userDao.save(user);
	}
	
	
	@Override
	public User findUserById(long id) throws ResourceNotFoundException {

		if (userDao.findById(id).isPresent()) {
			User user = userDao.findById(id).get();
			return user;
		} else {
			throw new ResourceNotFoundException("User with Id: " + id + " doesn't exist!!");
		}
	}

	@Override
	public List<User> findAllUsers() throws ResourceNotFoundException {
		List<User> user = (List<User>) userDao.findAll();
		if (user == null)
			throw new ResourceNotFoundException("User not created");
		return user;
	}
	
	

	@Override
	public void deleteUser(long id) throws ResourceNotFoundException {

		if (userDao.findById(id).isPresent()) {
			userDao.deleteById(id);
		} else {
			throw new ResourceNotFoundException("User with Id: " + id + " doesn't exist!!");
		}
	}

	@Override
	public User editUser(User user, long id) {
		User existUser = userDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		existUser.setUsername(user.getUsername());
		existUser.setPassword(user.getPassword());
		existUser.setAdmin(user.isAdmin());
		existUser.setEmail(user.getEmail());
		existUser.setNameOnCard(user.getNameOnCard());
		existUser.setCardNumber(user.getCardNumber());
		existUser.setCvv(user.getCvv());
		existUser.setAddress(user.getAddress());
		return userDao.save(existUser);
	}



	@Override
	public User findByUsername(String username) {
		   Optional<User> users = userDao.findByUsername(username);
		    if (users.isPresent()) {
		     User user = users.get();
		   return user;
		  }
		   return null;
		 }
	}
	
	
	
	
	
	


