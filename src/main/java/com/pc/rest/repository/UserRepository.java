package com.pc.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.rest.model.Userr;

@Repository
public interface UserRepository extends JpaRepository<Userr, Long>{
	Userr findByEmail(String email);
	boolean existsByEmail(String email);
}
