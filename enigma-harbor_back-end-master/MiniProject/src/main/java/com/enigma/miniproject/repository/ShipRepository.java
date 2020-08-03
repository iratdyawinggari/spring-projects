package com.enigma.miniproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enigma.miniproject.entity.Ship;

public interface ShipRepository extends JpaRepository<Ship, String>{
    @Query("select s from Ship s where s.shipCode=:shipCode")
    Ship findShipByShipCode(@Param("shipCode")String shipCode);
    
//	@Query("select s from Ship s where s.shipStatus.shipStatusName like :shipStatusName")
//	Page<Ship> getByStatusShipNameLike(@Param("shipStatusName") String shipStatusName,Pageable pageable);
//	Page<Ship> findAll(java.awt.print.Pageable paging);
    
	Page<Ship> findByShipStatusShipStatusNameContains(String shipStatusName,Pageable pageable);
	
	Page<Ship> findByShipCodeContains(String shipCode,Pageable pageable);
	
	Page<Ship> findByShipNameContains(String shipName,Pageable pageable);

	Page<Ship> findByCaptainNameContains(String captainName,Pageable pageable);
	
}
