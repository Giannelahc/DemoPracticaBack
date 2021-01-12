package com.pc.rest.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORS implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse respo = (HttpServletResponse) response;
		HttpServletRequest reque = (HttpServletRequest) request;
		
		respo.setHeader("Access-Control-Allow-Origin","*");
		respo.setHeader("Access-Control-Allow-Methods","DELETE, GET, OPTIONS, PATCH, POST, PUT");
		respo.setHeader("Access-Control-Max-Age","3600");
		respo.setHeader("Access-Control-Allow-Headers","x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");
		
		if ("OPTIONS".equalsIgnoreCase(reque.getMethod())) {
			respo.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(request, response);
		}
	}

}
