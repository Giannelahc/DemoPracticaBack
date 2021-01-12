package com.pc.rest.security.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationExcep implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException authException)
			throws IOException, ServletException {
		Map<String, Object> mapErrors = new HashMap<>();
		mapErrors.put("Error", "401");
		mapErrors.put("mensaje", "No estás autorizado para acceder a este recurso");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "NO AUTORIZADO");
		final ObjectMapper obj = new ObjectMapper();
		obj.writeValue(response.getOutputStream(), mapErrors);
	}

}
