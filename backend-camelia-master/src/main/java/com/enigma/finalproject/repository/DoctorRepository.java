package com.enigma.finalproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.enigma.finalproject.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
	
	@Query("SELECT d FROM Doctor d where d.id=:id")
	Doctor findDoctorById(@RequestParam("id") Integer id);
	
	List<Doctor> findByHospitalId(Integer hospitalId);
}
