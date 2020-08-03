package com.enigma.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.Reservation;
import com.enigma.finalproject.entity.Reservation;
import com.enigma.finalproject.repository.ReservationRepository;

@Service
public class ReservationService {
	
	@Autowired
	HospitalService hospitalService;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	public List<Reservation> getAllreservation(){
		return reservationRepository.findAll();
	}

	public Reservation addreservation(Reservation reservation) {
		return reservationRepository.save(reservation);
	}
	
	public Reservation addReservation(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	public Page<Reservation> showByPageSort(Integer page, Integer pageSize, String orderBy, String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return reservationRepository.findAll(paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return reservationRepository.findAll(paging);
		}
	}

	private String chooseShowBy(String orderBy) {
		switch (orderBy) {
		case "id":
			return "Id";
		case "name":
			return "Name";
		case "hospital":
			return "HospitalName";
		case "reservationDate":
			return "ReservationDate";
		case "address":
			return "UserInfoAddress";
		case "bpjsNumber":
			return "BpjsNumber";
		case "identityCardNumber":
			return "IdentityCardNumber";
		case "arrivalStatus":
			return "ArrivalStatus";
		default:
			return "Id";
		}
	}

	public Page<Reservation> showByPage(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return reservationRepository.findAll(paging);
	}
}
