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

import com.enigma.finalproject.entity.OutputStatus;
import com.enigma.finalproject.entity.OutputStatus;
import com.enigma.finalproject.service.OutputStatusService;

@RestController
@RequestMapping("/outputStatus")
public class OutputStatusController {
	@Autowired
	private OutputStatusService outputStatusService;

	@GetMapping
	public ResponseEntity<List<OutputStatus>> getAllOutputs() {
		List<OutputStatus> result = outputStatusService.getAllOutputStatus();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<OutputStatus> addOutputStatus(@RequestBody OutputStatus outputStatus) {
		OutputStatus result = outputStatusService.addOutputStatus(outputStatus);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<OutputStatus> editOutputStatus(@RequestBody OutputStatus outputStatus) {
		OutputStatus result = outputStatusService.editOutputStatus(outputStatus);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/diseaseId")
	public ResponseEntity<List<OutputStatus>> getOutputByDiseaseId(@RequestParam("diseaseId") Integer diseaseId) {
		List<OutputStatus> result = outputStatusService.getOutputStatusByDiseaseId(diseaseId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/id")
	public ResponseEntity<OutputStatus> getOutputById(@RequestParam("id") Integer id) {
		OutputStatus result = outputStatusService.getOutputStatusById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
