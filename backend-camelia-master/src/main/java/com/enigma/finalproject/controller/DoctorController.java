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

import com.enigma.finalproject.entity.Doctor;
import com.enigma.finalproject.entity.Doctor;
import com.enigma.finalproject.service.DoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
	@Autowired
	DoctorService doctorService;
	
	@PostMapping
	public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
		Doctor result = doctorService.addDoctor(doctor);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<Doctor> editDoctor(@RequestBody Doctor doctor) {
		Doctor result = doctorService.editDoctor(doctor);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Doctor>> getDoctor() {
		List<Doctor> result = doctorService.getAllDoctor();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/hospitalId")
	public ResponseEntity <List<Doctor>> getDoctorByHospitalId(@RequestParam("hospitalId") Integer hospitalId){
		List<Doctor> result = doctorService.findDoctorByHospitalId(hospitalId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<Doctor>> showByPage(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize) {
		Page<Doctor> result = doctorService.showByPage(page, pageSize);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<Page<Doctor>> showByPage(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderBy") String orderBy,
			@RequestParam("sort") String direction) {
		Page<Doctor> result = doctorService.showByPageSort(page, pageSize, orderBy, direction);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
