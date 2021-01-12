package com.pc.rest.security.service;

import java.io.IOException;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;

public interface JWTService {

	public String create(Authentication auth) throws IOException;
	public boolean validate(String token);
	public Claims getClaims(String token);
	public String getUsername(String token);
	public String resolve(String token);
}
