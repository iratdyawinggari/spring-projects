package com.enigma.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.enigma.finalproject.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	@Query("SELECT r FROM Role r where r.id=:id")
	Role getRoleById(@RequestParam("id")Integer id);
}
