package com.java.account.service;

import org.springframework.http.ResponseEntity;

import com.java.account.bean.User;

public interface UserService {

	public ResponseEntity<Object> createUser(User user);

	public ResponseEntity<Object> getUserByEmailHystrix(String email);

	public User findUserByEmailId(String email);

}
