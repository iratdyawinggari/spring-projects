package com.enigma.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.miniproject.entity.HarborStatus;
import com.enigma.miniproject.entity.ShipStatus;
import com.enigma.miniproject.service.HarborStatusService;

@RestController
public class HarborStatusController {
	@Autowired
	HarborStatusService harborStatusService;

	@PostMapping("harborStatus/id")
	public ResponseEntity<HarborStatus> getharborByharborCode(@RequestBody HarborStatus harborStatus) {
		HarborStatus searchHarborStatus = harborStatusService.findByharborStatusId(harborStatus.getHarborStatusId());
		return new ResponseEntity<>(searchHarborStatus, HttpStatus.OK);
	}

	@GetMapping("harborStatus")
	public ResponseEntity<List<HarborStatus>> getListShip() {
		List<HarborStatus> harborStatusList = harborStatusService.getAllHarborStatus();
		return new ResponseEntity<>(harborStatusList, HttpStatus.OK);
	}

	@GetMapping("harborStatus/withoutSuspend")
	public ResponseEntity<List<HarborStatus>> getListShipStatusWithout() {
		List<HarborStatus> shipStatusList = harborStatusService.findWithoutSuspend();
		return new ResponseEntity<>(shipStatusList, HttpStatus.OK);
	}
	
	@PostMapping("harborStatus/add")
	public ResponseEntity<HarborStatus> addShip(@RequestBody HarborStatus harborStatus) {
		HarborStatus newHarborStatus = harborStatusService.createHarborStatus(harborStatus);
		return new ResponseEntity<>(newHarborStatus, HttpStatus.OK);
	}

	@PostMapping("harborStatus/delete")
	public ResponseEntity<Integer> deleteCategoryById(@RequestBody HarborStatus harborStatus) {
		Integer Result = harborStatusService.deleteHarborStatus(harborStatus.getHarborStatusId());
		return new ResponseEntity<>(Result, HttpStatus.OK);
	}

	@PostMapping("harborStatus/update")
	public ResponseEntity<HarborStatus> updateharbor(@RequestBody HarborStatus harborStatus) {
		HarborStatus updateharbor = harborStatusService.updateHarborStatus(harborStatus);
		return new ResponseEntity<>(updateharbor, HttpStatus.OK);
	}

}
