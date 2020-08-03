package com.enigma.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.miniproject.entity.MenuHeader;
import com.enigma.miniproject.service.MenuHeaderService;

@RestController
public class MenuHeaderController {
	@Autowired
	MenuHeaderService menuHeaderService;

	@GetMapping("menu")
	public ResponseEntity<List<MenuHeader>> getListMenu() {
		List<MenuHeader> menuList = menuHeaderService.getAllMenuHeader();
		return new ResponseEntity<>(menuList, HttpStatus.OK);
	}
}
