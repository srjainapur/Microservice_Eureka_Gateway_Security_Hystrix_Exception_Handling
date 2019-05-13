package com.java.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.java.profile.bean.User;
import com.java.profile.repository.UserRepository;

@Service
@Qualifier("userServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<String> createUser(User user) {
		User save = userRepository.save(user);
		if(save != null) {
			return new ResponseEntity<String>("User with user id : " + user.getEmail() + " Created successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("User creation failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<User> getUserById(String email) {
		User findByEmail = userRepository.findByEmail(email);
		if(findByEmail != null) {
			return new ResponseEntity<User>(findByEmail, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
		}
	}
}
