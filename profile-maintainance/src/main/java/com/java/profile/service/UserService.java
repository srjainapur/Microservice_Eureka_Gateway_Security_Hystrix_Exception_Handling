package com.java.profile.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.java.profile.bean.User;

@Service
public interface UserService {
	public ResponseEntity<String> createUser(User user);
	public ResponseEntity<User> getUserById(String email);
}
