package com.pc.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;

import com.pc.rest.model.Userr;
import com.pc.rest.repository.UserRepository;
import com.pc.rest.security.filter.AuthorizationFilter;
import com.pc.rest.service.UserService;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService, UserService{

	private @Autowired UserRepository userRepository;
	Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Userr user = userRepository.findByEmail(username);
		log.info(user.getEmail() + user.getPassword() + user.getRole().getAuthority());
		if((user)==null) throw new UsernameNotFoundException(String.format("Usuario %s no encontrado", username));
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(user.getRole().getAuthority()));
		log.info(authorities.size() +"");
		if(authorities.isEmpty()) throw new UsernameNotFoundException(String.format("El usuario %s no tiene roles", username));
		User use = new User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
		log.info(use.toString());
		return use;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean register(Userr user) {
		if(!userRepository.existsByEmail(user.getEmail())) {user.setPassword(passwordEncoder.encode(user.getPassword()));userRepository.save(user); return true;}
		else return false;
	}

}
