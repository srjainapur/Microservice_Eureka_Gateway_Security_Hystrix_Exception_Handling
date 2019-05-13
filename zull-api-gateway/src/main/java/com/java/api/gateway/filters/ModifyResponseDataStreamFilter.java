package com.java.api.gateway.filters;

import java.io.ByteArrayInputStream;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.util.StreamUtils;

public class ModifyResponseDataStreamFilter extends ZuulFilter {
	
	private static final String filterType = "post";
	
	@Override
	public boolean shouldFilter() {
		RequestContext context = getCurrentContext();
		return context.getRequest().getParameter("service") != null;
	}

	@Override
	public Object run() throws ZuulException {
		try {
			RequestContext context = RequestContext.getCurrentContext();
			InputStream responseDataStream = context.getResponseDataStream();
			String body = StreamUtils.copyToString(responseDataStream, Charset.forName("UTF-8"));
			body = "Modified via setResponseDataStream(): " + body;
			
			context.setResponseDataStream(new ByteArrayInputStream(body.getBytes("UTF-8")));
			
		} catch (IOException e) {
			rethrowRuntimeException(e);
		}
		
		
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
