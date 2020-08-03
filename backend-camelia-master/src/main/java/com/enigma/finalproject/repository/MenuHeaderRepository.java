package com.enigma.finalproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.enigma.finalproject.entity.MenuHeader;

public interface MenuHeaderRepository extends JpaRepository<MenuHeader, Integer>{
	
//	@Query("SELECT h from MenuHeader h INNER JOIN h.childrens c ON where c.role.id = 1")
	List<MenuHeader> findAllByChildrensRoleId(Integer roleId);
	
//	SELECT count(*) 
//	  FROM BillDetails        bd 
//	  JOIN bd.billProductSet  bps 
//	 WHERE bd.client.id       = 1
//	   AND bps.product.id     = 1002
	
}
