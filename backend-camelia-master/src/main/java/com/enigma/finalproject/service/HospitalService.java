package com.enigma.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.annotations.ArrayBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.dto.TemporaryDistanceDTO;
import com.enigma.finalproject.entity.Hospital;
import com.enigma.finalproject.entity.User;
import com.enigma.finalproject.repository.HospitalRepository;

@Service
public class HospitalService {
	@Autowired
	HospitalRepository hospitalRepository;
	
	public List<Hospital> getAllHospital(){
		return hospitalRepository.findAll();
	}
	
	public Hospital getHospitalById(Integer id) {
		return hospitalRepository.findHospitalById(id);
	}
	
	public List<String> findDistricts(){
		return hospitalRepository.findDistrict();
	}
	
	public List<TemporaryDistanceDTO> getDistances(Double latitude,Double longitude){
		List<TemporaryDistanceDTO> distancesDTOs = new ArrayList<TemporaryDistanceDTO>();
		List<Hospital> hospitals = getAllHospital();
		for (Hospital hospital : hospitals) {
			TemporaryDistanceDTO distanceDTO = new TemporaryDistanceDTO();
			Double distance = distance(latitude, hospital.getLatitude(), longitude, hospital.getLongitude());
			if(distance<=5) {
				distanceDTO.setHospital(hospital);
				distanceDTO.setDistance(distance);
				distancesDTOs.add(distanceDTO);
			}

		}
		return distancesDTOs;
	}

	public double distance(double lat1, double lat2, double lon1, double lon2) { 
		
		// The math module contains a function 
		// named toRadians which converts from 
		// degrees to radians. 
		lon1 = Math.toRadians(lon1); 
		lon2 = Math.toRadians(lon2); 
		lat1 = Math.toRadians(lat1); 
		lat2 = Math.toRadians(lat2); 
		
		// Haversine formula  
		double dlon = lon2 - lon1;  
		double dlat = lat2 - lat1; 
		double a = Math.pow(Math.sin(dlat / 2), 2) 
		         + Math.cos(lat1) * Math.cos(lat2) 
		         * Math.pow(Math.sin(dlon / 2),2); 
		      
		double c = 2 * Math.asin(Math.sqrt(a)); 
		
		// Radius of earth in kilometers. Use 3956  
		// for miles 
		double r = 6371; 
		
		// calculate the result 
		return(c * r); 
	}  
	
	public List<Hospital> getHospitalByDistrict(String district){
		return hospitalRepository.findByDistrict(district);
	}
	
	public Hospital addHospital(Hospital hospital) {
		return hospitalRepository.save(hospital);
	}
	
	public Hospital editHospital(Hospital hospital) {
		return hospitalRepository.save(hospital);
	}
	
	public Page<Hospital> showByPageSort(Integer page, Integer pageSize, String orderBy, String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return hospitalRepository.findAll(paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return hospitalRepository.findAll(paging);
		}
	}

	public Page<Hospital> showByPage(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return hospitalRepository.findAll(paging);
	}
	
	public Page<Hospital> searchByHospitalName(String hospitalName, Integer page, Integer pageSize, String orderBy,
			String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return hospitalRepository.findByHospitalNameContains(hospitalName, paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return hospitalRepository.findByHospitalNameContains(hospitalName, paging);
		}
	}
	
	public Page<Hospital> searchByNoTelp(String noTelp, Integer page, Integer pageSize, String orderBy,
			String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return hospitalRepository.findByNoTelpContains(noTelp, paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return hospitalRepository.findByNoTelpContains(noTelp, paging);
		}
	}
	
	public Page<Hospital> searchByDistrict(String district, Integer page, Integer pageSize, String orderBy,
			String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return hospitalRepository.findByDistrictContains(district, paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return hospitalRepository.findByDistrictContains(district, paging);
		}
	}
	
	public Page<Hospital> searchByCity(String city, Integer page, Integer pageSize, String orderBy,
			String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return hospitalRepository.findByCityContains(city, paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return hospitalRepository.findByCityContains(city, paging);
		}
	}
	
	public Page<Hospital> searchByProvince(String province, Integer page, Integer pageSize, String orderBy,
			String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return hospitalRepository.findByProvinceContains(province, paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return hospitalRepository.findByProvinceContains(province, paging);
		}
	}
	
	public Page<Hospital> searchByAddress(String address, Integer page, Integer pageSize, String orderBy,
			String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return hospitalRepository.findByAddressContains(address, paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return hospitalRepository.findByAddressContains(address, paging);
		}
	}
	
	public Page<Hospital> searchByZipCode(String zipCode, Integer page, Integer pageSize, String orderBy,
			String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return hospitalRepository.findByZipCodeContains(zipCode, paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return hospitalRepository.findByZipCodeContains(zipCode, paging);
		}
	}
	
	private String chooseShowBy(String orderBy) {
		switch (orderBy) {
		case "id":
			return "Id";
		case "hospitalName":
			return "HospitalName";
		case "noTelp":
			return "NoTelp";
		case "district":
			return "District";
		case "city":
			return "City";
		case "province":
			return "Province";
		case "address":
			return "Address";
		case "zipCode":
			return "ZipCode";
		case "latitude":
			return "Latitude";
		case "longitude":
			return "Longitude";
		default:
			return "Id";
		}
	}

	public Hospital getHospitalByid(Integer id) {
		return hospitalRepository.findHospitalById(id);
	}
}