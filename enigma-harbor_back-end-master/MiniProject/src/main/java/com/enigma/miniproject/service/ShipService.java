package com.enigma.miniproject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.enigma.miniproject.entity.Ship;

@Service
public interface ShipService {
	Ship createShip(Ship ship);
	Ship updateShip(Ship ship);
    List<Ship> getAllShip();
    Ship findByShipCode (String shipCode);
    Ship deleteShip(String shipCode);
    Page<Ship> doShowByPage(Integer page, Integer pageSize);
    Page<Ship> getByShipCodeLike(String shipCode,int page,int pageSize);
    Page<Ship> getByShipNameLike(String shipName,int page,int pageSize);
	Page<Ship> findByCaptainNameContains(String captainName,int page,int pageSize);
	Page<Ship> getByStatusShipNameLike(String shipStatusName,int page,int pageSize);
}
