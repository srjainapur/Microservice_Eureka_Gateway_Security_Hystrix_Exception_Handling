package com.java.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.profile.bean.User;
import com.java.profile.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@RequestMapping(value="/createUser", method=RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@RequestMapping(value="/getUserByEmail/{email}", method=RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("email") String email) {
		return userService.getUserById(email);
	}
}
