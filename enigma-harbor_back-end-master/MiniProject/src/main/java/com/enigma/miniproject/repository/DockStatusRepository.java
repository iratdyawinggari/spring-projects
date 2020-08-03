package com.enigma.miniproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enigma.miniproject.entity.DockStatus;

public interface DockStatusRepository extends JpaRepository<DockStatus , Integer>{
    @Query("select ds from DockStatus ds where ds.dockStatusId=:dockStatusId")
    DockStatus findDockStatusByDockStatusId(@Param("dockStatusId")Integer dockStatusId);
    
	@Query("select ds from DockStatus ds where ds.dockStatusId between 1 and 2")
	List<DockStatus> findWithoutSuspend();
}
