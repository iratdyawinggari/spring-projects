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

import com.enigma.finalproject.dto.PatientDTO;
import com.enigma.finalproject.entity.Patient;
import com.enigma.finalproject.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {
	@Autowired
	PatientService patientService;

	@PostMapping
	public ResponseEntity<Patient> addPatient(@RequestBody PatientDTO patientDTO) {
		Patient result = patientService.addPatient(patientDTO);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<Patient> editPatient(@RequestBody PatientDTO patientDTO) {
		Patient result = patientService.addPatient(patientDTO);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Patient>> getPatient() {
		List<Patient> result = patientService.getAllPatient();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<Patient>> showByPage(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize) {
		Page<Patient> result = patientService.showByPage(page, pageSize);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<Page<Patient>> showByPage(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderBy") String orderBy,
			@RequestParam("sort") String direction) {
		Page<Patient> result = patientService.showByPageSort(page, pageSize, orderBy, direction);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/search/id")
	public ResponseEntity<Page<Patient>> searchById(@RequestParam("id") Integer id, @RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderBy") String orderBy,
			@RequestParam("sort") String direction) {
		Page<Patient> result = patientService.searchById(id, page, pageSize, orderBy, direction);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/search/name")
	public ResponseEntity<Page<Patient>> searchByName(@RequestParam("name") String name,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderBy") String orderBy, @RequestParam("sort") String direction) {
		Page<Patient> result = patientService.searchByName(name, page, pageSize, orderBy, direction);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}