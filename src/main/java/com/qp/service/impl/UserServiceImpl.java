package com.qp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qp.entity.UserEntity;
import com.qp.exception.ResourceNotFoundException;
import com.qp.repository.UserRepository;
import com.qp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	 	@Override
	    public UserEntity addUser(UserEntity user) {
	 		log.info("Creating new user: {}", user);
	        return userRepository.save(user);
	    }

	    @Override
	    public UserEntity getUserById(Long id) {
	        return userRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
	    }

}
