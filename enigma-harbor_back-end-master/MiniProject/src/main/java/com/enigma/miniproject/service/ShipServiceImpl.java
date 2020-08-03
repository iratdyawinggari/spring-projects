package com.enigma.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.enigma.miniproject.entity.Ship;
import com.enigma.miniproject.entity.ShipStatus;
import com.enigma.miniproject.repository.ShipRepository;
import com.enigma.miniproject.repository.ShipStatusRepository;
@Service
public class ShipServiceImpl implements ShipService {

	@Autowired
	ShipRepository shipRepository;
	
	@Autowired
	ShipStatusRepository shipStatusRepository;
	
	@Override
	public Ship createShip(Ship ship) {
		ShipStatus shipStatus = shipStatusRepository.findByShipStatusId(2);
		ship.setShipStatus(shipStatus);
		return shipRepository.save(ship);
	}

	@Override
	public Ship updateShip(Ship ship) {
		return shipRepository.save(ship);
	}

	@Override
	public List<Ship> getAllShip() {
		return shipRepository.findAll();
	}

	@Override
	public Ship findByShipCode(String shipCode) {
		return shipRepository.findShipByShipCode(shipCode);
	}
	
	@Override
	public Page<Ship> doShowByPage(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return shipRepository.findAll(paging);
	}
	
	@Override
	public Ship deleteShip(String shipCode) {
		Ship ship = shipRepository.findShipByShipCode(shipCode);
		ship.setShipStatus(shipStatusRepository.findByShipStatusId(5));
		return shipRepository.save(ship);
	}

	@Override
	public Page<Ship> getByShipCodeLike(String shipCode, int page, int pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return shipRepository.findByShipCodeContains(shipCode, paging);
	}

	@Override
	public Page<Ship> getByShipNameLike(String shipName, int page, int pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return shipRepository.findByShipNameContains(shipName, paging);
	}

	@Override
	public Page<Ship> findByCaptainNameContains(String captainName, int page, int pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return shipRepository.findByCaptainNameContains(captainName, paging);
	}

	@Override
	public Page<Ship> getByStatusShipNameLike(String shipStatusName, int page, int pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return shipRepository.findByShipStatusShipStatusNameContains(shipStatusName, paging);
	}

}
