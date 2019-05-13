package com.java.api.gateway.filters;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

public class PrefixRequestEntityFilter extends ZuulFilter {

	private static final String filterType = "pre";

	@Override
	public boolean shouldFilter() {
		RequestContext context = RequestContext.getCurrentContext();
		return context.getRequest().getParameter("service") != null;
	}

	@Override
	public Object run() {
		try {

			RequestContext context = RequestContext.getCurrentContext();
			InputStream in = (InputStream) context.get("requestEntity");

			if (in == null) {
				in = context.getRequest().getInputStream();
			}

			String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
			body = "request body modified via request wrapper: " + body;
			byte[] bytes = body.getBytes("UTF-8");

			context.setRequest(new HttpServletRequestWrapper(getCurrentContext().getRequest()) {
				@Override
				public ServletInputStream getInputStream() throws IOException {
					return new ServletInputStreamWrapper(bytes);
				}

				@Override
				public int getContentLength() {
					return bytes.length;
				}

				@Override
				public long getContentLengthLong() {
					return bytes.length;
				}
			});

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
		return 6;
	}

}
