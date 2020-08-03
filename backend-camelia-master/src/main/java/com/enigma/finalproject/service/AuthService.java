package com.enigma.finalproject.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.User;
import com.enigma.finalproject.repository.UserRepository;



@Service
public class AuthService {
	@Autowired
	UserRepository userRepository;

	public User doAuthenticate(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	
	public User doAuthenticateUserName(String username) {
		return userRepository.findByUsername(username);
	}
	
//	public User doAuthenticateUserPassword(String userPassword) {
//		return userRepository.findByUserPassword(userPassword);
//	}

}

