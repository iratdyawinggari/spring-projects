package com.enigma.finalproject.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.enigma.finalproject.entity.Disease;


public interface DiseaseRepository extends JpaRepository<Disease, Integer>{

	@Query("SELECT d from Disease d WHERE d.id=:id")
	Disease findDiseaseById(@RequestParam("id") Integer id);
}
