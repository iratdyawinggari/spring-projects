package com.enigma.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.miniproject.entity.Dock;
import com.enigma.miniproject.entity.DockStatus;
import com.enigma.miniproject.entity.ShipStatus;
import com.enigma.miniproject.repository.DockStatusRepository;
@Service
public class DockStatusServiceImpl implements DockStatusService{
	
	@Autowired
	DockStatusRepository dockStatusRepository;

	@Override
	public DockStatus createDockStatus(DockStatus dockStatus) {
		return dockStatusRepository.save(dockStatus);
	}

	@Override
	public DockStatus updateDockStatus(DockStatus dockStatus) {
		return dockStatusRepository.save(dockStatus);
	}

	@Override
	public List<DockStatus> getAllDockStatus() {
		return dockStatusRepository.findAll();
	}


	@Override
	public Integer deleteDockStatus(Integer dockStatusId) {
		try {
			DockStatus dockStatus = dockStatusRepository.findDockStatusByDockStatusId(dockStatusId);
			dockStatusRepository.delete(dockStatus);
			return 1;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return 0;
		}
	}

	@Override
	public DockStatus findByDockStatusId(Integer dockStatusId) {
		return dockStatusRepository.findDockStatusByDockStatusId(dockStatusId);
	}

	@Override
	public List<DockStatus> findWithoutSuspend() {
		return dockStatusRepository.findWithoutSuspend();
	}

}
