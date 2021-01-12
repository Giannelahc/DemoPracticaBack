package com.pc.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pc.rest.model.Product;
import com.pc.rest.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> registrar(@RequestBody Product product){
		Map<String, Object> response = new HashMap<String, Object>();
		if(this.productService.register(product)) {
			response.put("titulo", "EXITO");
			response.put("tipo", "success");
			response.put("mensaje", "Se refistró el producto correctamente");
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}else {
			response.put("titulo", "ERROR");
			response.put("tipo", "error");
			response.put("mensaje", "Hubo un error al registrar el producto");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> listar(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("titulo", "EXITO");
		response.put("product", productService.list());
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listarPorId(@PathVariable Long id){
		Map<String, Object> response = new HashMap<String, Object>();
		Product product = productService.listarPorId(id);
		if(product != null) {
			response.put("titulo", "EXITO");
			response.put("product", product);
			return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
		}else {
			response.put("titulo", "ERROR");
			response.put("product", "No se encontro el producto");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping
	public ResponseEntity<Map<String, Object>> actualizar(@RequestBody Product product){
		Map<String, Object> response = new HashMap<String, Object>();
		if(this.productService.update(product)) {
			response.put("titulo", "EXITO");
			response.put("tipo", "success");
			response.put("mensaje", "Se actualizó el producto correctamente");
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}else {
			response.put("titulo", "ERROR");
			response.put("tipo", "error");
			response.put("mensaje", "Hubo un error al actualizar el producto");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id){
		Map<String, Object> response = new HashMap<String, Object>();
		if( productService.delete(id)) {
			response.put("titulo", "EXITO");
			response.put("tipo", "success");
			response.put("mensaje", "Se eliminó el producto correctamente");
			return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
		}else {
			response.put("titulo", "ERROR");
			response.put("tipo", "error");
			response.put("mensaje", "Hubo un error al eliminar el producto");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
}
