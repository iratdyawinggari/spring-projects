package com.enigma.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.miniproject.entity.Dock;
import com.enigma.miniproject.entity.Harbor;
import com.enigma.miniproject.entity.Ship;
import com.enigma.miniproject.service.DockService;

@RestController
public class DockController {
	@Autowired
	DockService dockService;

	@PostMapping("docks/id")
	public ResponseEntity<Dock> getShipByShipCode(@RequestBody Dock dock) {
		Dock searchDock = dockService.findByDockCode(dock.getDockCode());
		return new ResponseEntity<>(searchDock, HttpStatus.OK);
	}

	@GetMapping("docks")
	public ResponseEntity<List<Dock>> getListDock() {
		List<Dock> dockList = dockService.getAllDock();
		return new ResponseEntity<>(dockList, HttpStatus.OK);
	}

	@PostMapping("docks/byHarborCode")
	public ResponseEntity<List<Dock>> getListDockByHarborCode(@RequestBody Harbor harbor ) {
		List<Dock> dockList = dockService.findDockByHarborCode(harbor.getHarborCode());
		return new ResponseEntity<>(dockList, HttpStatus.OK);
	}
	
	@GetMapping("docks/available")
	public ResponseEntity<List<Dock>> getListAvailableDock() {
		List<Dock> availableDockList = dockService.findAvailableDock();
		return new ResponseEntity<>(availableDockList, HttpStatus.OK);
	}

	
	@PostMapping("docks/add")
	public ResponseEntity<Dock> addDock(@RequestBody Dock dock) {
		Dock newDock = dockService.createDock(dock);
		return new ResponseEntity<>(newDock, HttpStatus.OK);
	}

	@PostMapping("docks/delete")
	public ResponseEntity<Dock> deleteCategoryById(@RequestBody Dock dock) {
		Dock Result = dockService.deleteDock(dock.getDockCode());
		return new ResponseEntity<>(Result, HttpStatus.OK);
	}

	@PostMapping("docks/update")
	public ResponseEntity<Dock> updateProduct(@RequestBody Dock dock) {
		Dock updateDock = dockService.updateDock(dock);
		return new ResponseEntity<>(updateDock, HttpStatus.OK);
	}
	
	@GetMapping("/docks/page")
	public ResponseEntity<Page<Dock>> listDockPage(@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<Dock> dockListPage = dockService.doShowByPage(page, pageSize);
		return new ResponseEntity<>(dockListPage, HttpStatus.OK);
	}


}
