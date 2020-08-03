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

import com.enigma.finalproject.entity.IndicatorStatus;
import com.enigma.finalproject.entity.IndicatorStatus;
import com.enigma.finalproject.service.IndicatorStatusService;

@RestController
@RequestMapping("/indicatorStatus")
public class IndicatorStatusController {
	@Autowired
	private IndicatorStatusService indicatorStatusService;

	@GetMapping
	public ResponseEntity<List<IndicatorStatus>> getAllIndicatorStatus() {
		List<IndicatorStatus> result = indicatorStatusService.getAllIndicatorStatus();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<IndicatorStatus> addIndicatorStatus(@RequestBody IndicatorStatus indicatorStatus) {
		IndicatorStatus result = indicatorStatusService.addIndicatorStatus(indicatorStatus);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<IndicatorStatus> editIndicatorStatus(@RequestBody IndicatorStatus indicatorStatus) {
		IndicatorStatus result = indicatorStatusService.editIndicatorStatus(indicatorStatus);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/diseaseId")
	public ResponseEntity<List<IndicatorStatus>> getIndicatorStatusByDiseaseId(
			@RequestParam("diseaseId") Integer diseaseId) {
		List<IndicatorStatus> result = indicatorStatusService.getIndicatorStatusByDiseaseId(diseaseId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/indicatorId")
	public ResponseEntity<List<IndicatorStatus>> getIndicatorStatusByIndicatorId(
			@RequestParam("indicatorId") Integer indicatorId) {
		List<IndicatorStatus> result = indicatorStatusService.getIndicatorStatusByIndicatorId(indicatorId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}


	@GetMapping("/id")
	public ResponseEntity<IndicatorStatus> getIndicatorById(@RequestParam("id") Integer id) {
		IndicatorStatus result = indicatorStatusService.getIndicatorStatusById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
