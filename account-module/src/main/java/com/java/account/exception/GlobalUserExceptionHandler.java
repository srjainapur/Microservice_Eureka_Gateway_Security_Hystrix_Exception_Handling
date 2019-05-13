package com.java.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalUserExceptionHandler {
	
	@ExceptionHandler(UserCreationException.class)
	public ResponseEntity<Object> userCreationException(UserCreationException uce) {
		return new ResponseEntity<Object>("User creation failed !!!!!!", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<Object> duplicateException(DuplicateUserException dexp) {
		return new ResponseEntity<Object>(dexp.getMessage(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> userNotFound(UserNotFoundException unfexp) {
		return new ResponseEntity<Object>(unfexp.getMessage(), HttpStatus.NOT_FOUND);
	}
}
