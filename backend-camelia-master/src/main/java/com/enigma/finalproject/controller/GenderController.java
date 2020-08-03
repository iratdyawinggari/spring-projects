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

import com.enigma.finalproject.entity.Gender;
import com.enigma.finalproject.service.GenderService;

@RestController
@RequestMapping("/genders")
public class GenderController {
	
	@Autowired
	GenderService genderService; 
	
	@GetMapping
	public ResponseEntity<List<Gender>> getAllGender(){
		List<Gender> result = genderService.getAllgender();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/id")
	public ResponseEntity<Gender> getGenderById(@RequestParam("id") Integer id){
		Gender result = genderService.getGenderById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Gender> addGender(@RequestBody Gender gender){
		Gender result = genderService.addGender(gender);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<Gender> updateGender(@RequestBody Gender gender){
		Gender result = genderService.addGender(gender);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
