package com.enigma.miniproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.enigma.miniproject.entity.Harbor;
import com.enigma.miniproject.entity.HarborStatus;
import com.enigma.miniproject.repository.HarborRepository;
import com.enigma.miniproject.repository.HarborStatusRepository;

@Service
public class HarborServiceImpl implements HarborService {
	@Autowired
	HarborRepository harborRepository;
	
	@Autowired
	HarborStatusRepository harborStatusRepository;

	@Override
	public Harbor createharbor(Harbor harbor) {
		return harborRepository.save(harbor);
	}

	@Override
	public Harbor updateharbor(Harbor harbor) {
		return harborRepository.save(harbor);
	}

	@Override
	public List<Harbor> getAllharbor() {
		return harborRepository.findAll();
	}

	@Override
	public Harbor findByharborCode(String harborCode) {
		return harborRepository.findharborByharborCode(harborCode);
	}

	@Override
	public Harbor deleteharbor(String harborCode) {
		Harbor harbor = harborRepository.findharborByharborCode(harborCode);
		HarborStatus harborStatus = harborStatusRepository.findByHarborStatusId(3);
		harbor.setHarborStatus(harborStatus);
		return	harborRepository.save(harbor);
	}

	@Override
	public List<Harbor> findWithoutSuspend() {
		return harborRepository.findWithoutSuspend();
	}

	@Override
	public Integer findCapacityHarbor(String harborCode) {
		return harborRepository.findCapacityHarbor(harborCode);
	}

	@Override
	public Page<Harbor> doShowByPage(Integer page, Integer pageSize) {
		List<Harbor> harbors= harborRepository.findAll();
		for (Harbor harbor : harbors) {
			String harborCode=harbor.getHarborCode();
			Integer newCapacity=harborRepository.findCapacityHarbor(harborCode);
			harbor.setHarborCapacity(newCapacity);
			harborRepository.save(harbor);
		}
		Pageable paging = PageRequest.of(page, pageSize);
		return harborRepository.findAll(paging);
	}
}
