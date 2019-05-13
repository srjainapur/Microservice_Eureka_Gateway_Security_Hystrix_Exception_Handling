package com.java.api.gateway.filters;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class AddResponseHeaderFilter extends ZuulFilter {
	
	private static final String filterType = "post";

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletResponse response = context.getResponse();
		response.addHeader("X-Foo", UUID.randomUUID().toString());		
		return null;
	}

	@Override
	public String filterType() {
		return filterType;
	}

	@Override
	public int filterOrder() {
		return 999;
	}
}
