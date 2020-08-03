package com.enigma.miniproject.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.enigma.miniproject.entity.Dock;

public interface DockService {
	Dock createDock(Dock dock);
	Dock updateDock(Dock dock);
    List<Dock> getAllDock();
    Dock findByDockCode (String dockCode);
    Dock deleteDock(String dockCode);
    List<Dock> findAvailableDock();
    List<Dock> findDockByHarborCode(String harborCode);
    Page<Dock> doShowByPage(Integer page, Integer pageSize);
}
