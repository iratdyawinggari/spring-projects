package com.enigma.finalproject.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.finalproject.entity.Reservation;
import com.enigma.finalproject.entity.Reservation;
import com.enigma.finalproject.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	@Autowired
	ReservationService reservationService;
	
	@GetMapping
	public ResponseEntity<List<Reservation>> getreservation(){
		List<Reservation> result = reservationService.getAllreservation();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation){
		Reservation result = reservationService.addReservation(reservation);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Reservation> editReservation(@RequestBody Reservation reservation){
		Reservation result = reservationService.addReservation(reservation);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<Reservation>> showByPage(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize) {
		Page<Reservation> result = reservationService.showByPage(page, pageSize);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<Page<Reservation>> showByPage(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderBy") String orderBy,
			@RequestParam("sort") String direction) {
		Page<Reservation> result = reservationService.showByPageSort(page, pageSize, orderBy, direction);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
