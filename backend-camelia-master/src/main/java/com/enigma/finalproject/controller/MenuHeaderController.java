package com.enigma.finalproject.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.finalproject.entity.Hospital;
import com.enigma.finalproject.entity.MenuHeader;
import com.enigma.finalproject.service.MenuHeaderService;

@RestController
@RequestMapping("/menu")
public class MenuHeaderController {

@Autowired
MenuHeaderService menuHeaderService;

@GetMapping
public ResponseEntity<List<MenuHeader>> getMenu(@RequestParam("roleId") Integer roleId) {
List<MenuHeader> result = menuHeaderService.getHeaderByRoleId(roleId);
return new ResponseEntity<>(result, HttpStatus.OK);
}
}
