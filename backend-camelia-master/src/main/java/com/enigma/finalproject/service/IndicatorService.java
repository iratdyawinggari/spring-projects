package com.enigma.finalproject.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.Indicator;
import com.enigma.finalproject.repository.IndicatorRepository;

@Service
public class IndicatorService {
	@Autowired
	private IndicatorRepository indicatorRepository;
	
	public List<Indicator> getAllIndicator(){
		return indicatorRepository.findAll();
	}
	
	public List<Indicator> getIndicatorByDiseaseId(Integer diseaseId){
		return indicatorRepository.findByDiseaseId(diseaseId);
	}
	
	public Indicator getIndicatorById(Integer id) {
		return indicatorRepository.findIndicatorById(id);
	}

	public Indicator addIndicator(Indicator indicator) {
		return indicatorRepository.save(indicator);
	}

	public Indicator editIndicator(Indicator indicator) {
		return indicatorRepository.save(indicator);
	}
}
