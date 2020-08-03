package com.enigma.miniproject.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.enigma.miniproject.entity.User;
import com.enigma.miniproject.repository.UserRepository;


@Service
public class AuthService {
	@Autowired
	UserRepository userRepository;

//	public User doAuthenticate(User user) {
//		return userRepository.findByUserNameAndUserPassword(user.getUserName(), user.getUserPassword());
//	}
	
	public User doAuthenticateUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	
	public User doAuthenticateUserPassword(String userPassword) {
		return userRepository.findByUserPassword(userPassword);
	}

}
