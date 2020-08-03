package com.enigma.miniproject.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.miniproject.entity.User;
import com.enigma.miniproject.service.AuthService;

@RestController
public class AuthController {
	@Autowired
	AuthService authService;

	@PostMapping("/auth/userName")
	public ResponseEntity<User> doAuthenticate(@RequestBody User user) {
		User userInfo = authService.doAuthenticateUserName(user.getUserName());
		if (userInfo.getUserName().length() >= 0) {
			return new ResponseEntity<>(userInfo, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(userInfo, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/auth/password")
	public ResponseEntity<User> doAuthenticatePassword(@RequestBody User user) {
		User userInfo = authService.doAuthenticateUserPassword(user.getUserPassword());
		if (userInfo.getUserPassword().length() >= 0) {
			return new ResponseEntity<>(userInfo, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(userInfo, HttpStatus.UNAUTHORIZED);
		}
	}
}
