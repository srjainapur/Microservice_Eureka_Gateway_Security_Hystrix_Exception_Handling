package com.java.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.account.bean.User;
import com.java.account.exception.UserNotFoundException;
import com.java.account.repository.UserRepository;

@Service
public class HystrixExceptionPropogation {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findUserByStatus(Integer isActive) {
		User userObj = userRepository.findByActive(isActive);
		if(userObj == null) {
			throw new UserNotFoundException("User with Status active not found in the System");
		}
		return userObj;
	}
}
