package com.enigma.finalproject.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.Gender;
import com.enigma.finalproject.entity.Role;
import com.enigma.finalproject.entity.User;
import com.enigma.finalproject.entity.UserInfo;
import com.enigma.finalproject.service.GenderService;
import com.enigma.finalproject.service.RoleService;
import com.enigma.finalproject.service.UserInfoService;
import com.enigma.finalproject.service.UserService;

@Service
public class UserDTOService {
	
	@Autowired
	GenderService genderService;

	@Autowired
	UserService userService;
	
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	public User update(UserDTO userDTO) {
		User user = userService.getUserById(userDTO.getId());
		
		UserInfo userInfo = user.getUserInfo();
		userInfo.setFullname(userDTO.getFullname());
		userInfo.setBirthDate(userDTO.getBirthDate());
		userInfo.setEmail(userDTO.getEmail());
		userInfo.setNoTelp(userDTO.getNoTelp());
		userInfo.setAddress(userDTO.getAddress());
		userInfoService.addUserInfo(userInfo);
		Gender gender = genderService.getGenderById(userDTO.getGenderId());
		Role role = roleService.getRoleById(userDTO.getRoleId());
		user.setUsername(userDTO.getUsername());
		user.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
		user.setUserInfo(userInfo);
		user.setGender(gender);
		user.setRole(role);
		return userService.addUser(user);
	}
	
	
	public User register(UserDTO userDTO) {
		UserInfo userInfo = new UserInfo();
		userInfo.setFullname(userDTO.getFullname());
		userInfo.setBirthDate(userDTO.getBirthDate());
		userInfo.setEmail(userDTO.getEmail());
		userInfo.setNoTelp(userDTO.getNoTelp());
		userInfo.setAddress(userDTO.getAddress());
		userInfo.setPhotoPath("/");
		userInfoService.addUserInfo(userInfo);
		Gender gender = genderService.getGenderById(userDTO.getGenderId());
		User user = new User();
		Role role = roleService.getRoleById(userDTO.getRoleId());
		user.setUsername(userDTO.getUsername());
		user.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
		user.setUserInfo(userInfo);
		user.setGender(gender);
		user.setRole(role);
		return userService.addUser(user);
	}
}
