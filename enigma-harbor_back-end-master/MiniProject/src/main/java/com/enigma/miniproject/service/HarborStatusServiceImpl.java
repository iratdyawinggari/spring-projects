package com.enigma.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.miniproject.entity.HarborStatus;
import com.enigma.miniproject.repository.HarborStatusRepository;

@Service
public class HarborStatusServiceImpl implements HarborStatusService {

	@Autowired
	HarborStatusRepository harborStatusRepository;

	@Override
	public HarborStatus createHarborStatus(HarborStatus harborStatus) {
		return harborStatusRepository.save(harborStatus);
	}

	@Override
	public HarborStatus updateHarborStatus(HarborStatus harborStatus) {
		return harborStatusRepository.save(harborStatus);
	}

	@Override
	public List<HarborStatus> getAllHarborStatus() {
		return harborStatusRepository.findAll();
	}

	@Override
	public HarborStatus findByharborStatusId(Integer harborStatusId) {
		return harborStatusRepository.findByHarborStatusId(harborStatusId);
	}

	@Override
	public Integer deleteHarborStatus(Integer harborStatusId) {
		try {
			HarborStatus harborStatus = harborStatusRepository.findByHarborStatusId(harborStatusId);
			harborStatusRepository.delete(harborStatus);
			return 1;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return 0;
		}
	}

	@Override
	public List<HarborStatus> findWithoutSuspend() {
		return harborStatusRepository.findWithoutSuspend();
	}

}
