package com.pc.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pc.rest.model.Userr;
import com.pc.rest.service.UserService;

@RestController
public class UserController {

	private @Autowired UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(@RequestBody Userr user){
		Map<String, Object> response = new HashMap<>();
		if(userService.register(user)) {
			response.put("titulo", "EXITO");
			response.put("tipo", "success");
			response.put("mensaje", "Se refistró al usuario con éxito");
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}else {
			response.put("titulo", "ERROR");
			response.put("tipo", "error");
			response.put("mensaje", "Hubo un error al registrar al usuario");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
}
