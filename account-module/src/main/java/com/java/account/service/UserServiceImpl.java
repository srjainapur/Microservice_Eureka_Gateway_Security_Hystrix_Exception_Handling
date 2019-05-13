package com.java.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.java.account.bean.User;
import com.java.account.exception.DuplicateUserException;
import com.java.account.exception.UserCreationException;
import com.java.account.exception.UserNotFoundException;
import com.java.account.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
@Qualifier("userServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<Object> createUser(User user) {
		
		try {
			User userObj = userRepository.save(user);
			
			if(userObj == null) {
				return new ResponseEntity<Object>("Failed to create user", HttpStatus.BAD_REQUEST);				
			} else {
				return new ResponseEntity<Object>("User created Successfully with Id " + userObj.getEmail(), 
					HttpStatus.OK);
			}
		} catch(DataIntegrityViolationException dexp) {
			throw new DuplicateUserException("User with " + user.getEmail() + " id exist in the System");
		} catch (Exception exp) {
			throw new UserCreationException();
		}
	}
	
	@HystrixCommand(fallbackMethod="getUserByEmailHystrix_fallBack", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1000")
	})
	public ResponseEntity<Object> getUserByEmailHystrix(String email) {
		
		try {
			Thread.sleep(2000);
			
			User userByEmail = userRepository.findByEmail(email);
			if(userByEmail != null) {
				return new ResponseEntity<Object>(userByEmail, HttpStatus.OK);
			} else {
				throw new UserNotFoundException("User with id " + email + " not exists in the system");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResponseEntity<Object> getUserByEmailHystrix_fallBack(String email) {
		return new ResponseEntity<Object>("findByEmail() is not responding !!!!", HttpStatus.NOT_FOUND);
	}
	
	public User findUserByEmailId(String email) {
		User findByEmail = userRepository.findByEmail(email);
		
		if(findByEmail == null) {
			throw new UserNotFoundException("User with id " + email + " not exists in the system");
		}
		
		return findByEmail;
	}
}
