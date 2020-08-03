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

import com.enigma.finalproject.entity.Disease;
import com.enigma.finalproject.entity.Disease;
import com.enigma.finalproject.service.DiseaseService;

@RestController
@RequestMapping("/diseases")
public class DiseaseController {
	@Autowired
	private DiseaseService diseaseService; 
	
	@GetMapping
	public ResponseEntity<List<Disease>> getAllDisease(){
		List<Disease> result = diseaseService.getAllDiseases();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Disease> getDiseaseById(@RequestParam("id") Integer id){
		Disease result = diseaseService.getDiseaseById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Disease> addDisease(@RequestBody Disease disease){
		Disease result = diseaseService.addDisease(disease);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Disease> editDisease(@RequestBody Disease disease){
		Disease result = diseaseService.editDisease(disease);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
