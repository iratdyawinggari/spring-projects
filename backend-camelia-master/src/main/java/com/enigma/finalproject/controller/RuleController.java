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

import com.enigma.finalproject.entity.MasterRule;
import com.enigma.finalproject.entity.MasterRule;
import com.enigma.finalproject.service.MasterRuleService;
@RestController
@RequestMapping("/rules")
public class RuleController {

	@Autowired
	private MasterRuleService masterRuleService;

	@GetMapping
	public ResponseEntity<List<MasterRule>> getreservation() {
		List<MasterRule> result = masterRuleService.getAllRules();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<MasterRule> addMasterRule(@RequestBody MasterRule masterRule) {
		MasterRule result = masterRuleService.addMasterRule(masterRule);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<MasterRule> editMasterRule(@RequestBody MasterRule masterRule) {
		MasterRule result = masterRuleService.editMasterRule(masterRule);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/diseaseId")
	public ResponseEntity<List<MasterRule>> getRuleByDiseaseId(@RequestParam("diseaseId") Integer diseaseId) {
		List<MasterRule> result = masterRuleService.getMasterRuleByDiseaseId(diseaseId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/id")
	public ResponseEntity<MasterRule> getRuleById(@RequestParam("id") Integer id) {
		MasterRule result = masterRuleService.getMasterRuleById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
