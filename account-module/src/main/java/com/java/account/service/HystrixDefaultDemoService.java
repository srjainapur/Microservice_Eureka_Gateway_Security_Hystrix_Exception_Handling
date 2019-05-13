package com.java.account.service;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
@DefaultProperties(defaultFallback="classDefaultFallback", 
	commandProperties={@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1000")})
public class HystrixDefaultDemoService {
	
	@HystrixCommand
	public String getUserNameById(String isActive) throws InterruptedException  {
		Thread.sleep(4000);
		return "Returning User name by Id";
	}
	
	@HystrixCommand
	public String getUserId(String email) throws InterruptedException {
		Thread.sleep(4000);
		return "Returning User Id";
	}
	
	public String classDefaultFallback() throws InterruptedException {
		return "Executing default fallback method";
	}
}
