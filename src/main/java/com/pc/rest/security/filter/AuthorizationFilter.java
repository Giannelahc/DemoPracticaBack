package com.pc.rest.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.pc.rest.security.service.JWTService;
import com.pc.rest.security.service.impl.JWTServiceImpl;
import org.slf4j.LoggerFactory;

public class AuthorizationFilter extends BasicAuthenticationFilter{
	Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);
	private JWTService securityService;
	
	private UserDetailsService userDetailsService;
	
	public AuthorizationFilter(AuthenticationManager authenticationManager, JWTService securityService, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.securityService = securityService;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String header = request.getHeader(JWTServiceImpl.HEADER_STRING);
		if(!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}
		log.info(securityService.getUsername(header));
		log.info(this.userDetailsService.loadUserByUsername(securityService.getUsername(header)) + "");
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(securityService.getUsername(header));
		
		UsernamePasswordAuthenticationToken authentication = null;
		
		if(securityService.validate(header)) {
			authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request,response);
	}
	
	protected boolean requiresAuthentication(String header) {
		if(header == null || !header.startsWith(JWTServiceImpl.TOKEN_PREFIX))
			return false;
		return true;
	}
}
