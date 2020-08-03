package com.enigma.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.Gender;
import com.enigma.finalproject.repository.GenderRepository;

@Service
public class GenderService {

	@Autowired
	GenderRepository genderRepository;

	public Gender getGenderById(Integer id) {
		return genderRepository.findGenderById(id);
	}
	
	public Gender addGender(Gender gender) {
		return genderRepository.save(gender);
	}
	
	public List<Gender> getAllgender(){
		return genderRepository.findAll();
	}

}
