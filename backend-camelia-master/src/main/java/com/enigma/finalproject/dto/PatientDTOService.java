package com.enigma.finalproject.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.Patient;
import com.enigma.finalproject.repository.PatientRepository;
import com.enigma.finalproject.service.GenderService;
import com.enigma.finalproject.service.UserService;

@Service
public class PatientDTOService {
	@Autowired
	private GenderService genderService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private PatientRepository patientRepository;
	
	public Patient addPatient(PatientDTO patientDTO) {
		Patient patient = new Patient();
		patient.setAge(patientDTO.getAge());
		patient.setGender(genderService.getGenderById(patientDTO.getGenderId()));
		patient.setName(patientDTO.getName());
		patient.setId(patientDTO.getId());
		patient.setUser(userService.getUserById(patientDTO.getUserId()));
		return patientRepository.save(patient);
	}
}
