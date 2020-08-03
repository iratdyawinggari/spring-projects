package com.enigma.finalproject.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.Disease;
import com.enigma.finalproject.repository.DiseaseRepository;
@Service
public class DiseaseService {
	@Autowired
	private DiseaseRepository diseaseRepository;
	public List<Disease> getAllDiseases() {
		return diseaseRepository.findAll();
	}
	
	public Disease getDiseaseById(Integer id) {
		return diseaseRepository.findDiseaseById(id);
	}

	public Disease addDisease(Disease disease) {
		return diseaseRepository.save(disease);
	}

	public Disease editDisease(Disease disease) {
		return diseaseRepository.save(disease);
	}
}
