package com.enigma.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.finalproject.dto.UserDTO;
import com.enigma.finalproject.entity.User;
import com.enigma.finalproject.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getUser() {
		List<User> result = userService.getAllUser();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/id")
	public ResponseEntity<User> getUserById(@RequestParam("id") Integer id) {
		User result = userService.getUserById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO) {
		User result = userService.addUserDTO(userDTO);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<User> editUser(@RequestBody UserDTO user) {
		User result = userService.editUser(user);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<User>> showByPage(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize) {
		Page<User> result = userService.showByPage(page, pageSize);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<Page<User>> showByPage(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderBy") String orderBy,
			@RequestParam("sort") String direction) {
		Page<User> result = userService.showByPageSort(page, pageSize, orderBy, direction);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/search/fullname")
	public ResponseEntity<Page<User>> searchByFullname(@RequestParam("name") String fullname,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderBy") String orderBy, @RequestParam("sort") String direction) {
		Page<User> result = userService.searchByFullname(fullname, page, pageSize, orderBy, direction);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/search/username")
	public ResponseEntity<Page<User>> searchByDockName(@RequestParam("name") String username,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderBy") String orderBy, @RequestParam("sort") String direction) {
		Page<User> result = userService.searchByUsername(username, page, pageSize, orderBy, direction);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}