package com.enigma.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enigma.miniproject.entity.MenuHeader;

public interface MenuHeaderRepository extends JpaRepository<MenuHeader, String> {
//    @Query("select s from Ship s where s.shipCode=:shipCode")
//    Ship findShipByShipCode(@Param("shipCode")String shipCode);
}
