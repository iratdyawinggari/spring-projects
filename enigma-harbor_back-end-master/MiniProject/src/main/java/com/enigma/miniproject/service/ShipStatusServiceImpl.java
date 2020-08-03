package com.enigma.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.miniproject.entity.ShipStatus;
import com.enigma.miniproject.repository.ShipStatusRepository;

@Service
public class ShipStatusServiceImpl implements ShipStatusService {

	@Autowired
	ShipStatusRepository shipStatusRepository;

	@Override
	public ShipStatus createShipStatus(ShipStatus shipStatus) {
		return shipStatusRepository.save(shipStatus);
	}

	@Override
	public ShipStatus updateShipStatus(ShipStatus shipStatus) {
		return shipStatusRepository.save(shipStatus);
	}

	@Override
	public List<ShipStatus> getAllShipStatus() {
		return shipStatusRepository.findAll();
	}

	@Override
	public ShipStatus findByShipStatusId(Integer shipStatusId) {
		return shipStatusRepository.findByShipStatusId(shipStatusId);
	}

	@Override
	public Integer deleteShipStatusId(Integer shipStatusId) {
		try {
			ShipStatus shipStatus = shipStatusRepository.findByShipStatusId(shipStatusId);
			shipStatusRepository.delete(shipStatus);
			return 1;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return 0;
		}
	}

	@Override
	public List<ShipStatus> findWithoutSuspend() {
		return shipStatusRepository.findWithoutSuspend();
	}

}
