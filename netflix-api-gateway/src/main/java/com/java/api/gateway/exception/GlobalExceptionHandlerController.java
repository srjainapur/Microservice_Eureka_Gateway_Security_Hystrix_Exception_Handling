package com.java.api.gateway.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	/*@ExceptionHandler(CustomException.class)
	public void handleCustomException(HttpServletResponse res, CustomException e) throws IOException {
		LOG.error("ERROR", e);
		res.sendError(e.getHttpStatus().value(), e.getMessage());
	}*/
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Object> handleCustomException(HttpServletResponse res, CustomException e) {
		//LOG.error("ERROR", e);
		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public void handleAccessDeniedException(HttpServletResponse res, AccessDeniedException e) throws IOException {
		LOG.error("ERROR", e);
		res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public void handleIllegalArgumentException(HttpServletResponse res, IllegalArgumentException e) throws IOException {
		LOG.error("ERROR", e);
		res.sendError(HttpStatus.BAD_REQUEST.value(), "Something went wrong");
	}

	@ExceptionHandler(Exception.class)
	public void handleException(HttpServletResponse res, Exception e) throws IOException {
		LOG.error("ERROR", e);
		res.sendError(HttpStatus.BAD_REQUEST.value(), "Something went wrong");
	}
}
