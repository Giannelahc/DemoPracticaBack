package com.pc.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.rest.model.Product;
import com.pc.rest.repository.ProductRepository;
import com.pc.rest.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	@Transactional(readOnly = false)
	public boolean register(Product product) {
		if(productRepository.save(product)!=null) return true;
		else return false;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(Product product) {
		if(productRepository.save(product)!=null) return true;
		else return false;
	}

	@Override
	@Transactional(readOnly = true)
	public Product listarPorId(Long id) {
		return productRepository.findById(id).get();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Product> list() {
		return productRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public boolean delete(Long id) {
		if(productRepository.existsById(id)) {
			productRepository.deleteById(id);
			return true;
		}else return false;
	}

}
