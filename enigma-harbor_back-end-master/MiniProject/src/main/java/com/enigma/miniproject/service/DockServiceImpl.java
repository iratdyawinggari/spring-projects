package com.enigma.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.enigma.miniproject.entity.Dock;
import com.enigma.miniproject.entity.Ship;
import com.enigma.miniproject.repository.DockRepository;
import com.enigma.miniproject.repository.DockStatusRepository;

@Service
public class DockServiceImpl implements DockService{
	
	@Autowired
	DockStatusRepository dockStatusRepository;
	
	@Autowired
	DockRepository dockRepository;

	@Override
	public Dock createDock(Dock dock) {
		return dockRepository.save(dock);
	}

	@Override
	public Dock updateDock(Dock dock) {
		return dockRepository.save(dock);
	}

	@Override
	public List<Dock> getAllDock() {
		return dockRepository.findAll();
	}

	@Override
	public Dock findByDockCode(String dockCode) {
		return dockRepository.findDockByDockCode(dockCode);
	}

	@Override
	public Dock deleteDock(String dockCode) {
			Dock dock = dockRepository.findDockByDockCode(dockCode);
			dock.setDockStatus(dockStatusRepository.findDockStatusByDockStatusId(3));
			return dockRepository.save(dock);
	}

	@Override
	public List<Dock> findAvailableDock() {
		return dockRepository.findAvailableDock();
	}

	@Override
	public List<Dock> findDockByHarborCode(String harborCode) {
		return dockRepository.findDockByHarborCode(harborCode);
	}

	@Override
	public Page<Dock> doShowByPage(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return dockRepository.findAll(paging);
	}
}
