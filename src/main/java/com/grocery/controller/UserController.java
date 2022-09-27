package com.grocery.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
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

import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.User;
import com.grocery.service.UserService;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"},allowedHeaders = "*")

public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/addUser")
	User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	
	

	@GetMapping("/findUserById/{id}")
	public ResponseEntity<?> findUserById(@PathVariable("id") long id) throws ResourceNotFoundException {

		User users = userService.findUserById(id);
		return ResponseEntity.ok(users);

	}

	@GetMapping("/admin/findAllUsers")
	public ResponseEntity<List<User>> findAllUsers() throws ResourceNotFoundException {
		List<User> u = userService.findAllUsers();
		return ResponseEntity.ok(u);

	}

	@PutMapping("/editUser/{id}")
	public ResponseEntity<User> editUser(@RequestBody User user, @PathVariable long id) {

		return ResponseEntity.ok(userService.editUser(user, id));

	}

	


	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) throws ResourceNotFoundException {

		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}

	

	@GetMapping("/findByUsername/{username}")
	public ResponseEntity<?> findByUsername(@PathVariable("username") String username)
			throws ResourceNotFoundException {

		User users = userService.findByUsername(username);
		return ResponseEntity.ok(users);
	}

}
