package com.enigma.miniproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enigma.miniproject.entity.ShipStatus;

public interface ShipStatusRepository extends JpaRepository<ShipStatus, Integer> {
	   @Query("select ss from ShipStatus ss where ss.shipStatusId=:shipStatusId")
	   ShipStatus findByShipStatusId(@Param("shipStatusId")Integer shipStatusId);
	   
	   @Query("select ss from ShipStatus ss where ss.shipStatusId between 2 and 4")
	   List<ShipStatus> findWithoutSuspend();
	   
}
