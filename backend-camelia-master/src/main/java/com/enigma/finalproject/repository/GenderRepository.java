package com.enigma.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.enigma.finalproject.entity.Gender;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
	@Query("SELECT g from Gender g where g.id=:id")
	Gender findGenderById(@RequestParam("id") Integer id);
}
