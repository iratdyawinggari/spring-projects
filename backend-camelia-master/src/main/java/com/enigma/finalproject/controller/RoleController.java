package com.enigma.finalproject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.finalproject.entity.Role;
import com.enigma.finalproject.entity.Role;
import com.enigma.finalproject.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	@Autowired
	RoleService roleService;

	@GetMapping
	public ResponseEntity<List<Role>> getreservation() {
		List<Role> result = roleService.getAllRoles();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Role> addRole(@RequestBody Role role) {
		Role result = roleService.addRole(role);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<Role> editRole(@RequestBody Role role) {
		Role result = roleService.editRole(role);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/id")
	public ResponseEntity<Role> getRuleById(@RequestParam("id") Integer id) {
		Role result = roleService.getRoleById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
