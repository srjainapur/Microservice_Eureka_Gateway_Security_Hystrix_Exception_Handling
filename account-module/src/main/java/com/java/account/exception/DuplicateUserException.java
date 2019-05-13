package com.java.account.exception;

public class DuplicateUserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public DuplicateUserException(String message) {
		this.message=message;
	}
}
