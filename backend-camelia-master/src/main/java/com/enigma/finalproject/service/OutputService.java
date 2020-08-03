package com.enigma.finalproject.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.Output;
import com.enigma.finalproject.repository.OutputRepository;
@Service
public class OutputService {
	@Autowired
	private OutputRepository outputRepository;
	
	public List<Output> getAllOutputs(){
		return outputRepository.findAll();
	}
	
	public Output getOutputByDiseaseId(Integer diseaseId){
		return outputRepository.findByDiseaseId(diseaseId);
	}
	
	public Output getOutputById(Integer id) {
		return outputRepository.findOutputById(id);
	}

	public Output addOutput(Output output) {
		return outputRepository.save(output);
	}

	public Output editOutput(Output output) {
		return outputRepository.save(output);
	}
}
