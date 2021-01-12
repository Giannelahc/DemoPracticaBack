package com.pc.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.rest.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
