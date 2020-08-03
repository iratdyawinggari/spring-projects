package com.enigma.miniproject.service;

import java.util.List;

import com.enigma.miniproject.entity.ShipStatus;

public interface ShipStatusService {
	ShipStatus createShipStatus(ShipStatus shipStatus);
	ShipStatus updateShipStatus(ShipStatus shipStatus);
    List<ShipStatus> getAllShipStatus();
    ShipStatus findByShipStatusId (Integer shipStatusId);
    Integer deleteShipStatusId(Integer shipStatusId);
    List<ShipStatus> findWithoutSuspend();
}
