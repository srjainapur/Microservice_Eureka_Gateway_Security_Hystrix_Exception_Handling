package com.java.account.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.account.bean.User;
import com.java.account.exception.UserNotFoundException;
import com.java.account.service.HystrixDefaultDemoService;
import com.java.account.service.HystrixExceptionPropogation;
import com.java.account.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixException;

@RestController
public class UserController {
	
	private static final Logger LOG = Logger.getLogger(UserController.class.getName()); 
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	private HystrixDefaultDemoService hystrixDefaultDemoService;
	
	@Autowired
	private HystrixExceptionPropogation hystrixExceptionPropogation;
	
	// Create user
	@RequestMapping(value="/createUser", method=RequestMethod.POST)
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		LOG.log(Level.INFO, "BEGIN :: createUser() method");
		return userService.createUser(user);
	}
	
	@RequestMapping(value="/userHystrix/{email}", method=RequestMethod.GET)
	public ResponseEntity<Object> getUserByEmailHystrix(@PathVariable("email") String email) {
		LOG.log(Level.INFO, "BEGIN :: getUserByIdHystrix() method");
		return userService.getUserByEmailHystrix(email);
	}
	
	@RequestMapping(value="/getUserNameByIsActive/{isActive}", method=RequestMethod.GET)
	public String getUserNameByIsActive(@PathVariable("isActive") int isActive) throws InterruptedException {
		return hystrixDefaultDemoService.getUserNameById("1");
	}
	
	@RequestMapping(value="/getUserId/{email}", method=RequestMethod.GET)
	public String getUserId(@PathVariable("email") String email) throws InterruptedException {
		return hystrixDefaultDemoService.getUserId(email);
	}
	
	@HystrixCommand(fallbackMethod="statusFallbackMethod", ignoreExceptions={UserNotFoundException.class})
	@RequestMapping(value="/getUserByStatus/{isActive}", method=RequestMethod.GET)
	public User findUserByStatus(@PathVariable("isActive") int isActive) {
		return hystrixExceptionPropogation.findUserByStatus(isActive);
	}
	
	public User statusFallbackMethod(int isActive) {
		System.out.println("Executing statusFallbackMethod() method");
		return new User();
	}
	
	@HystrixCommand(fallbackMethod="emailNotFoundFallback", raiseHystrixExceptions={HystrixException.RUNTIME_EXCEPTION})
	@RequestMapping(value="/getByEmailId/{email}", method=RequestMethod.GET)
	public User findUserByEmailId(@PathVariable("email") String email) {
		return userService.findUserByEmailId(email);
	}
	
	public User emailNotFoundFallback(String email) {
		System.out.println("Executing emailNotFoundFallback() method email " + email);
		return new User();
	}
}
