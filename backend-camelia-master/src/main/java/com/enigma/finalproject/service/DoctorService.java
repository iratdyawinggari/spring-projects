package com.enigma.finalproject.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.Doctor;
import com.enigma.finalproject.entity.Doctor;
import com.enigma.finalproject.entity.Doctor;
import com.enigma.finalproject.repository.DoctorRepository;


@Service
public class DoctorService {
	@Autowired
	DoctorRepository doctorRepository;
	
	public List<Doctor> getAllDoctor(){
		return doctorRepository.findAll();
	}
	
	public Doctor addDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	public Doctor editDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	public List<Doctor> findDoctorByHospitalId(Integer hospitalId) {
		return doctorRepository.findByHospitalId(hospitalId);
	}

	public Page<Doctor> showByPage(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return doctorRepository.findAll(paging);
	}

	public Page<Doctor> showByPageSort(Integer page, Integer pageSize, String orderBy, String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return doctorRepository.findAll(paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return doctorRepository.findAll(paging);
		}
	}

	private String chooseShowBy(String orderBy) {
		switch (orderBy) {
		case "id":
			return "Id";
		case "username":
			return "Username";
		case "fullname":
			return "UserInfoFullname";
		case "birthDate":
			return "UserInfoBirthDate";
		case "address":
			return "UserInfoAddress";
		case "email":
			return "UserInfoEmail";
		case "noTelp":
			return "UserInfoNoTelp";
		case "gender":
			return "GenderGenderName";
		default:
			return "Id";
		}
	}
}
