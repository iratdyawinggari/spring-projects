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

import com.enigma.finalproject.dto.TemporaryDistanceDTO;
import com.enigma.finalproject.entity.Hospital;
import com.enigma.finalproject.service.HospitalService;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {
	@Autowired
	HospitalService hospitalService;

	@GetMapping
	public ResponseEntity<List<Hospital>> getHospital() {
		List<Hospital> result = hospitalService.getAllHospital();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Hospital> getHospitalById(@RequestParam("id") Integer id) {
		Hospital result = hospitalService.getHospitalByid(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}


	@PostMapping
	public ResponseEntity<Hospital> addHospital(@RequestBody Hospital hospital) {
		Hospital result = hospitalService.addHospital(hospital);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<Hospital> editHospital(@RequestBody Hospital hospital) {
		Hospital result = hospitalService.editHospital(hospital);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/districts")
	public ResponseEntity<List<String>> getDistricts() {
		List<String> result = hospitalService.findDistricts();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/distances")
	public ResponseEntity<List<TemporaryDistanceDTO>> getDistances(@RequestParam("latitude") Double latitude,
			@RequestParam("longitude") Double longitude) {
		List<TemporaryDistanceDTO> result = hospitalService.getDistances(latitude, longitude);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/district")
	public ResponseEntity<List<Hospital>> getHospitalByDistrict(@RequestParam("district") String district) {
		List<Hospital> result = hospitalService.getHospitalByDistrict(district);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/search/name")
	public ResponseEntity<Page<Hospital>> searchByHospitalName(@RequestParam("hospitalName") String hospitalName,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderBy") String orderBy, @RequestParam("sort") String direction) {
		Page<Hospital> result = hospitalService.searchByHospitalName(hospitalName, page, pageSize, orderBy, direction);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/search/city")
	public ResponseEntity<Page<Hospital>> searchByCity(@RequestParam("city") String city,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderBy") String orderBy, @RequestParam("sort") String direction) {
		Page<Hospital> result = hospitalService.searchByCity(city, page, pageSize, orderBy, direction);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/search/province")
	public ResponseEntity<Page<Hospital>> searchByProvince(@RequestParam("province") String province,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderBy") String orderBy, @RequestParam("sort") String direction) {
		Page<Hospital> result = hospitalService.searchByProvince(province, page, pageSize, orderBy, direction);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/search/address")
	public ResponseEntity<Page<Hospital>> searchByAddress(@RequestParam("address") String address,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderBy") String orderBy, @RequestParam("sort") String direction) {
		Page<Hospital> result = hospitalService.searchByAddress(address, page, pageSize, orderBy, direction);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/search/zipCode")
	public ResponseEntity<Page<Hospital>> searchByZipCode(@RequestParam("zip") String zipCode,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderBy") String orderBy, @RequestParam("sort") String direction) {
		Page<Hospital> result = hospitalService.searchByZipCode(zipCode, page, pageSize, orderBy, direction);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	

	@GetMapping("/page")
	public ResponseEntity<Page<Hospital>> showByPage(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize) {
		Page<Hospital> result = hospitalService.showByPage(page, pageSize);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<Page<Hospital>> showByPage(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderBy") String orderBy,
			@RequestParam("sort") String direction) {
		Page<Hospital> result = hospitalService.showByPageSort(page, pageSize, orderBy, direction);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}