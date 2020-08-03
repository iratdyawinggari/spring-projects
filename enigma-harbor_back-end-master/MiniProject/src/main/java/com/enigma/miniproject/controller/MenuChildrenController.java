package com.enigma.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.miniproject.entity.MenuChildren;
import com.enigma.miniproject.entity.MenuHeader;
import com.enigma.miniproject.service.MenuChildrenService;
@RestController
public class MenuChildrenController {
	
	@Autowired
	MenuChildrenService menuChildrenService;
	
	@GetMapping("menu/children")
	public ResponseEntity<List<MenuChildren>> getListMenu() {
		List<MenuChildren> menuList = menuChildrenService.getAllMenuChildren();
		return new ResponseEntity<>(menuList, HttpStatus.OK);
	}
}
