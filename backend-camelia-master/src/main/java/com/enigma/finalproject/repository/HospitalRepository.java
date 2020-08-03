package com.enigma.finalproject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.enigma.finalproject.entity.Hospital;
import com.enigma.finalproject.entity.User;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

	@Query("SELECT h from Hospital h where h.district=:district")
	List<Hospital> findByDistrict(@RequestParam("district") String district);
	
	@Query("SELECT DISTINCT h.district from Hospital h")
	List<String> findDistrict();

	@Query("SELECT h from Hospital h where h.id=:id")
	Hospital findHospitalById(@RequestParam("id") Integer id);

	Page<Hospital> findByHospitalNameContains(String province, Pageable paging);
	
	Page<Hospital> findByNoTelpContains(String noTelp, Pageable paging);

	Page<Hospital> findByDistrictContains(String district, Pageable paging);

	Page<Hospital> findByCityContains(String city, Pageable paging);

	Page<Hospital> findByProvinceContains(String province, Pageable paging);

	Page<Hospital> findByAddressContains(String address, Pageable paging);

	Page<Hospital> findByZipCodeContains(String zipCode, Pageable paging);
}