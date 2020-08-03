package com.enigma.miniproject.service;

import java.util.List;

import com.enigma.miniproject.entity.DockStatus;
import com.enigma.miniproject.entity.HarborStatus;

public interface DockStatusService {
	DockStatus createDockStatus(DockStatus dockStatus);
	DockStatus updateDockStatus(DockStatus dockStatus);
    List<DockStatus> getAllDockStatus();
    DockStatus findByDockStatusId (Integer dockStatusId);
    Integer deleteDockStatus(Integer dockStatusId);
    List<DockStatus> findWithoutSuspend();
}
