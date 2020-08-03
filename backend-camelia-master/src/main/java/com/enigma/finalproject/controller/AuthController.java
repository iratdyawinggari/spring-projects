package com.enigma.finalproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.finalproject.entity.User;
import com.enigma.finalproject.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	Logger logger =LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	AuthService authService;

	@PostMapping("/username")
	public ResponseEntity<User> doAuthenticateUsername(@RequestBody User user) {
		User userInfo = authService.doAuthenticateUserName(user.getUsername());
		if (userInfo.getUsername().length() >= 0) {
			return new ResponseEntity<>(userInfo, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(userInfo, HttpStatus.UNAUTHORIZED);
		}
	}
	
//	@PostMapping
//	public ResponseEntity<User> doAuthenticate(@RequestBody User user) {
//		User userInfo = authService.doAuthenticate(user);
//	
//		try {
//			if (userInfo.getUsername().length() >= 0) {
//				return new ResponseEntity<>(userInfo, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<>(userInfo, HttpStatus.UNAUTHORIZED);
//			}
//		} catch (Exception e) {
//			logger.error(e.toString());
//			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//		}
//	}
	
	@PostMapping("/password")
	public ResponseEntity<User> doAuthenticatePassword(@RequestBody User user) {
		User userInfo = authService.doAuthenticate(user);
		if (userInfo.getPassword().length() >= 0) {
			return new ResponseEntity<>(userInfo, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(userInfo, HttpStatus.UNAUTHORIZED);
		}
	}
}
