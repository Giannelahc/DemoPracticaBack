package com.pc.rest.service;

import java.util.List;

import com.pc.rest.model.Product;

public interface ProductService {

	boolean register(Product product);
	boolean update(Product product);
	List<Product> list();
	Product listarPorId(Long id);
	boolean delete(Long id);
}
