package com.enigma.finalproject.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.dto.UserDTO;
import com.enigma.finalproject.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private GenderService genderService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.enigma.finalproject.entity.User user = userRepository.findByUsername(username);
		if (user != null) {
			return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User "+username+" Not Found");
		}
	}

//	public com.enigma.finalproject.entity.User save(UserDTO user) {
//		
//		com.enigma.finalproject.entity.User newUser = new com.enigma.finalproject.entity.User();
////		newUser.setUsername(user.getUsername());
////		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
////		newUser.setRole(roleService.getRoleById(user.getRoleId()));
////		newUser.setGender(genderService.getGenderById(user.getGenderId()));
////		newUser.setUserInfo(userInfo);
//		return userService.addUserDTO(user);
//	}
}