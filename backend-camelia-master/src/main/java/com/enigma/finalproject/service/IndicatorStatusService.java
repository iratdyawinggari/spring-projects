package com.enigma.finalproject.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.IndicatorStatus;
import com.enigma.finalproject.repository.IndicatorStatusRepository;

@Service
public class IndicatorStatusService {
	@Autowired
	private IndicatorStatusRepository indicatorStatusRepository;

	public List<IndicatorStatus> getAllIndicatorStatus() {
		return indicatorStatusRepository.findAll();
	}

	public List<IndicatorStatus> getIndicatorStatusByDiseaseId(Integer diseaseId) {
		return indicatorStatusRepository.findByIndicatorDiseaseId(diseaseId);
	}
	
	public List<IndicatorStatus> getIndicatorStatusByIndicatorId(Integer indicatorId) {
		return indicatorStatusRepository.findByIndicatorId(indicatorId);
	}

	public IndicatorStatus getIndicatorStatusById(Integer id) {
		return indicatorStatusRepository.findIndicatorStatusById(id);
	}

	public IndicatorStatus addIndicatorStatus(IndicatorStatus indicatorStatus) {
		return indicatorStatusRepository.save(indicatorStatus);
	}

	public IndicatorStatus editIndicatorStatus(IndicatorStatus indicatorStatus) {
		return indicatorStatusRepository.save(indicatorStatus);
	}
}
