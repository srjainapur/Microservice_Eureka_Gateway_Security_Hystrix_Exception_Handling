package com.java.api.gateway.service;

import com.java.api.gateway.bean.User;

public interface ILoginService {
	String login(String username, String password);

	User saveUser(User user);

	boolean logout(String token);

	Boolean isValidToken(String token);

	String createNewToken(String token);
}
