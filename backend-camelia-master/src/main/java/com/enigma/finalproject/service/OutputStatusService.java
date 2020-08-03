package com.enigma.finalproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.OutputStatus;
import com.enigma.finalproject.repository.OutputStatusRepository;
@Service
public class OutputStatusService {
	@Autowired
	private OutputStatusRepository outputStatusRepository;

	public List<OutputStatus> getAllOutputStatus() {
		return outputStatusRepository.findAll();
	}

	public List<OutputStatus> getOutputStatusByDiseaseId(Integer diseaseId) {
		return outputStatusRepository.findByOutputDiseaseId(diseaseId);
	}

	public OutputStatus getOutputStatusById(Integer id) {
		return outputStatusRepository.findOutputStatusById(id);
	}

	public OutputStatus editOutputStatus(OutputStatus outputStatus) {
		return outputStatusRepository.save(outputStatus);
	}

	public OutputStatus addOutputStatus(OutputStatus outputStatus) {
		return outputStatusRepository.save(outputStatus);
	}

}
