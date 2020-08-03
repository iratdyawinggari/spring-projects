package com.enigma.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.finalproject.config.JwtTokenUtil;
import com.enigma.finalproject.dto.UserDTO;
import com.enigma.finalproject.entity.JwtRequest;
import com.enigma.finalproject.entity.JwtResponse;
import com.enigma.finalproject.entity.User;
import com.enigma.finalproject.entity.UserInfo;
import com.enigma.finalproject.repository.UserRepository;
import com.enigma.finalproject.service.AuthService;
import com.enigma.finalproject.service.JwtUserDetailsService;
import com.enigma.finalproject.service.UserService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		User user = userRepository.findByUsername(userDetails.getUsername());
		return ResponseEntity.ok(new JwtResponse(token,user));
	}

//	@RequestMapping(value = "/users", method = RequestMethod.POST)
//	public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) throws Exception {
//		return ResponseEntity.ok(userDetailsService.save(userDTO));
//	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}