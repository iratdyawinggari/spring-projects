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

import com.enigma.miniproject.entity.Ship;
import com.enigma.miniproject.service.ShipService;

@RestController
public class ShipController {
	@Autowired
	ShipService shipService;

	@PostMapping("ships/id")
	public ResponseEntity<Ship> getShipByShipCode(@RequestBody Ship ship) {
		Ship searchShip = shipService.findByShipCode(ship.getShipCode());
		return new ResponseEntity<>(searchShip, HttpStatus.OK);
	}

	@GetMapping("ships")
	public ResponseEntity<List<Ship>> getListShip() {
		List<Ship> shipList = shipService.getAllShip();
		return new ResponseEntity<>(shipList, HttpStatus.OK);
	}

	@PostMapping("ships/add")
	public ResponseEntity<Ship> addShip(@RequestBody Ship ship) {
		Ship newShip = shipService.createShip(ship);
		return new ResponseEntity<>(newShip, HttpStatus.OK);
	}

	@PostMapping("ships/delete")
	public ResponseEntity<Ship> deleteCategoryById(@RequestBody Ship ship) {
		Ship Result = shipService.deleteShip(ship.getShipCode());
		return new ResponseEntity<>(Result, HttpStatus.OK);
	}

	@PostMapping("ships/update")
	public ResponseEntity<Ship> updateProduct(@RequestBody Ship ship) {
		Ship updateShip = shipService.updateShip(ship);
		return new ResponseEntity<>(updateShip, HttpStatus.OK);
	}
	
	@GetMapping("ships/shipName")
	public ResponseEntity<Page<Ship>> getByShipNameLike(
			@RequestParam("shipName") String shipName, @RequestParam("page") int page,
			@RequestParam("pageSize") int pageSize) {
		Page<Ship> listShip = shipService.getByShipNameLike(shipName, page, pageSize);
		return new ResponseEntity<>(listShip, HttpStatus.OK);
	}
	
	@GetMapping("ships/shipCode")
	public ResponseEntity<Page<Ship>> getByShipCodeLike(
			@RequestParam("shipCode") String shipCode, @RequestParam("page") int page,
			@RequestParam("pageSize") int pageSize) {
		Page<Ship> listShip = shipService.getByShipCodeLike(shipCode, page, pageSize);
		return new ResponseEntity<>(listShip, HttpStatus.OK);
	}
	
	@GetMapping("ships/captainName")
	public ResponseEntity<Page<Ship>> getByCaptainNameLike(
			@RequestParam("captainName") String captainName, @RequestParam("page") int page,
			@RequestParam("pageSize") int pageSize) {
		Page<Ship> listShip = shipService.findByCaptainNameContains(captainName, page, pageSize);
		return new ResponseEntity<>(listShip, HttpStatus.OK);
	}
	
	@GetMapping("ships/status")
	public ResponseEntity<Page<Ship>> getByShipStatusLike(
			@RequestParam("status") String status, @RequestParam("page") int page,
			@RequestParam("pageSize") int pageSize) {
		Page<Ship> listShip = shipService.getByStatusShipNameLike(status, page, pageSize);
		return new ResponseEntity<>(listShip, HttpStatus.OK);
	}
	
	@GetMapping("/ships/page")
	public ResponseEntity<Page<Ship>> listShipPage(@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<Ship> shipList = shipService.doShowByPage(page, pageSize);
		return new ResponseEntity<>(shipList, HttpStatus.OK);
	}

	
}

