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

import com.enigma.finalproject.entity.Indicator;
import com.enigma.finalproject.entity.Indicator;
import com.enigma.finalproject.service.IndicatorService;

@RestController
@RequestMapping("/indicators")
public class IndicatorController {
	@Autowired
	private IndicatorService indicatorService;

	@GetMapping
	public ResponseEntity<List<Indicator>> getAllIndicator() {
		List<Indicator> result = indicatorService.getAllIndicator();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Indicator> addIndicator(@RequestBody Indicator indicator) {
		Indicator result = indicatorService.addIndicator(indicator);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<Indicator> editIndicator(@RequestBody Indicator indicator) {
		Indicator result = indicatorService.editIndicator(indicator);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/diseaseId")
	public ResponseEntity<List<Indicator>> getIndicatorByDiseaseId(@RequestParam("diseaseId") Integer diseaseId) {
		List<Indicator> result = indicatorService.getIndicatorByDiseaseId(diseaseId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/id")
	public ResponseEntity<Indicator> getIndicatorById(@RequestParam("id") Integer id) {
		Indicator result = indicatorService.getIndicatorById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
