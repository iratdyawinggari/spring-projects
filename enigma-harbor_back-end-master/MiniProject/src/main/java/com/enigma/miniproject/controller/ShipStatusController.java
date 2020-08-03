package com.enigma.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.miniproject.entity.Ship;
import com.enigma.miniproject.entity.ShipStatus;
import com.enigma.miniproject.service.ShipService;
import com.enigma.miniproject.service.ShipStatusService;

@RestController
public class ShipStatusController {
	@Autowired
	ShipStatusService shipStatusService;

	@PostMapping("shipStatus/id")
	public ResponseEntity<ShipStatus> getShipByShipCode(@RequestBody ShipStatus shipStatus) {
		ShipStatus searchShipStatus = shipStatusService.findByShipStatusId(shipStatus.getShipStatusId());
		return new ResponseEntity<>(searchShipStatus, HttpStatus.OK);
	}

	@GetMapping("shipStatus")
	public ResponseEntity<List<ShipStatus>> getListShipStatus() {
		List<ShipStatus> shipStatusList = shipStatusService.getAllShipStatus();
		return new ResponseEntity<>(shipStatusList, HttpStatus.OK);
	}
	
	@GetMapping("shipStatus/withoutSuspend")
	public ResponseEntity<List<ShipStatus>> getListShipStatusWithout() {
		List<ShipStatus> shipStatusList = shipStatusService.findWithoutSuspend();
		return new ResponseEntity<>(shipStatusList, HttpStatus.OK);
	}

	@PostMapping("shipStatus/add")
	public ResponseEntity<ShipStatus> addShipStatus(@RequestBody ShipStatus shipStatus) {
		ShipStatus newShipStatus = shipStatusService.createShipStatus(shipStatus);
		return new ResponseEntity<>(newShipStatus, HttpStatus.OK);
	}

	@PostMapping("shipStatus/delete")
	public ResponseEntity<Integer> deleteCategoryById(@RequestBody ShipStatus shipStatus) {
		Integer Result = shipStatusService.deleteShipStatusId(shipStatus.getShipStatusId());
		return new ResponseEntity<>(Result, HttpStatus.OK);
	}

	@PostMapping("shipStatus/update")
	public ResponseEntity<ShipStatus> updateProduct(@RequestBody ShipStatus shipStatus) {
		ShipStatus updateShip = shipStatusService.updateShipStatus(shipStatus);
		return new ResponseEntity<>(updateShip, HttpStatus.OK);
	}

}
