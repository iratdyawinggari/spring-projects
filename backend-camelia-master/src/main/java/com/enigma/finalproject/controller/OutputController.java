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

import com.enigma.finalproject.entity.Output;
import com.enigma.finalproject.entity.Output;
import com.enigma.finalproject.service.OutputService;
@RestController
@RequestMapping("/outputs")
public class OutputController {
	@Autowired
	private OutputService outputService;

	@GetMapping
	public ResponseEntity<List<Output>> getAllOutputs() {
		List<Output> result = outputService.getAllOutputs();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Output> addOutput(@RequestBody Output output) {
		Output result = outputService.addOutput(output);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<Output> editOutput(@RequestBody Output output) {
		Output result = outputService.editOutput(output);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/diseaseId")
	public ResponseEntity<Output> getOutputByDiseaseId(@RequestParam("diseaseId") Integer diseaseId) {
		Output result = outputService.getOutputByDiseaseId(diseaseId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/id")
	public ResponseEntity<Output> getOutputById(@RequestParam("id") Integer id) {
		Output result = outputService.getOutputById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
