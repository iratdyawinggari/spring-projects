package com.enigma.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.dto.PatientDTO;
import com.enigma.finalproject.entity.Patient;
import com.enigma.finalproject.repository.PatientRepository;

@Service
public class PatientService {
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	private GenderService genderService;
	
	@Autowired 
	private UserService userService;

	
	public Page<Patient> showByPageSort(Integer page, Integer pageSize, String orderBy,
			String direction){
		if(direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return patientRepository.findAll(paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return patientRepository.findAll(paging);
		}
	}

	public List<Patient> getAllPatient(){
		return patientRepository.findAll();
	}
	
	public Page<Patient>showByPage(Integer page, Integer pageSize){
		Pageable paging = PageRequest.of(page, pageSize);
		return patientRepository.findAll(paging);
	}
	
	public Page<Patient> searchById(Integer id, Integer page, Integer pageSize, String orderBy,
			String direction){
		if(direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return patientRepository.findByIdContains(id, paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return patientRepository.findByIdContains(id, paging);
		}
	}
	
	public Page<Patient> searchByName(String name, Integer page, Integer pageSize, String orderBy,
			String direction){
		if(direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return patientRepository.findByNameContains(name, paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return patientRepository.findByNameContains(name, paging);
		}
	}
		
	public Patient addPatient(PatientDTO patientDTO) {
		Patient patient = new Patient();
		patient.setAge(patientDTO.getAge());
		patient.setGender(genderService.getGenderById(patientDTO.getGenderId()));
		patient.setName(patientDTO.getName());
		patient.setId(patientDTO.getId());
		patient.setUser(userService.getUserById(patientDTO.getUserId()));
		return patientRepository.save(patient);
	}
	
	private String chooseShowBy(String orderBy) {
		switch (orderBy) {
		case "id":
			return "Id";
		case "username":
			return "Username";
		case "password":
			return "Password";
		default:
			return "Id";
		}
	}

	public Patient addPatient(Patient patient) {
		return patientRepository.save(patient);
	}
}