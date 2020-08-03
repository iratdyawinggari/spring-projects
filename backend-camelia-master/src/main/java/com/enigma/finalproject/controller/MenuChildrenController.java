package com.enigma.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.finalproject.entity.MenuChildren;
import com.enigma.finalproject.entity.MenuHeader;
import com.enigma.finalproject.service.MenuChildrenService;

@RestController
@RequestMapping("/children")
public class MenuChildrenController {

	@Autowired
	MenuChildrenService menuChildrenService;
	
	@GetMapping
	public ResponseEntity<List<MenuChildren>> getHospital(@RequestParam("roleId") Integer roleId) {
		List<MenuChildren> result = menuChildrenService.getChildrenByRoleId(roleId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
