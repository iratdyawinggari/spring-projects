package com.enigma.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.miniproject.entity.Harbour;
import com.enigma.miniproject.entity.Ship;
import com.enigma.miniproject.service.HarbourService;
import com.enigma.miniproject.service.ShipService;

@RestController
public class HarbourController {
	@Autowired
	HarbourService harbourService;
	

	@PostMapping("harbours/id")
	public ResponseEntity<Harbour> getHarbourByHarbourCode(@RequestBody Harbour harbour) {
		Harbour searchHarbour = harbourService.findByHarbourCode(harbour.getHarbourCode());
		return new ResponseEntity<>(searchHarbour, HttpStatus.OK);
	}

	@GetMapping("harbours")
	public ResponseEntity<List<Harbour>> getListShip() {
		List<Harbour> harbourList = harbourService.getAllHarbour();
		return new ResponseEntity<>(harbourList, HttpStatus.OK);
	}

	@PostMapping("harbours/add")
	public ResponseEntity<Harbour> addShip(@RequestBody Harbour harbour) {
		Harbour newHarbour = harbourService.createHarbour(harbour);
		return new ResponseEntity<>(newHarbour, HttpStatus.OK);
	}

	@PostMapping("harbours/delete")
	public ResponseEntity<Integer> deleteCategoryById(@RequestBody Harbour harbour) {
		Integer Result = harbourService.deleteHarbour(harbour.getHarbourCode());
		return new ResponseEntity<>(Result, HttpStatus.OK);
	}

	@PostMapping("harbours/update")
	public ResponseEntity<Harbour> updateHarbour(@RequestBody Harbour harbour) {
		Harbour updateHarbour = harbourService.updateHarbour(harbour);
		return new ResponseEntity<>(updateHarbour, HttpStatus.OK);
	}

}
