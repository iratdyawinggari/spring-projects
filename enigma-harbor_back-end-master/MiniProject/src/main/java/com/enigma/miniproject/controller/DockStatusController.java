package com.enigma.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.miniproject.entity.DockStatus;
import com.enigma.miniproject.entity.HarborStatus;
import com.enigma.miniproject.entity.Ship;
import com.enigma.miniproject.service.DockStatusService;

@RestController
public class DockStatusController {
	@Autowired
	DockStatusService dockStatusService;
	
	@PostMapping("dockStatus/id")
	public ResponseEntity<DockStatus> getByDockStatusId(@RequestBody DockStatus dockStatus) {
		DockStatus searchDockStatus = dockStatusService.findByDockStatusId(dockStatus.getDockStatusId());
		return new ResponseEntity<>(searchDockStatus, HttpStatus.OK);
	}

	@GetMapping("dockStatus")
	public ResponseEntity<List<DockStatus>> getListDockStatus() {
		List<DockStatus> dockStatusList = dockStatusService.getAllDockStatus();
		return new ResponseEntity<>(dockStatusList, HttpStatus.OK);
	}

	@GetMapping("dockStatus/withoutSuspend")
	public ResponseEntity<List<DockStatus>> getListDockStatusWithout() {
		List<DockStatus> dockStatusListWithoutSuspend = dockStatusService.findWithoutSuspend();
		return new ResponseEntity<>(dockStatusListWithoutSuspend, HttpStatus.OK);
	}
	
	@PostMapping("dockStatus/add")
	public ResponseEntity<DockStatus> addShip(@RequestBody DockStatus dockStatus) {
		DockStatus newDockStatus = dockStatusService.createDockStatus(dockStatus); 
		return new ResponseEntity<>(newDockStatus, HttpStatus.OK);
	}

	@PostMapping("dockStatus/delete")
	public ResponseEntity<Integer> deleteCategoryById(@RequestBody DockStatus dockStatus) {
		Integer Result = dockStatusService.deleteDockStatus(dockStatus.getDockStatusId());
		return new ResponseEntity<>(Result, HttpStatus.OK);
	}

	@PostMapping("dockStatus/update")
	public ResponseEntity<DockStatus> updateProduct(@RequestBody DockStatus dockStatus) {
		DockStatus updateDockStatus = dockStatusService.updateDockStatus(dockStatus);
		return new ResponseEntity<>(updateDockStatus, HttpStatus.OK);
	}

}
