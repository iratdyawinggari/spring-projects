package com.enigma.miniproject.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enigma.miniproject.entity.Dock;

public interface DockRepository extends JpaRepository<Dock, String> {
    @Query("select d from Dock d where d.dockCode=:dockCode")
    Dock findDockByDockCode(@Param("dockCode")String dockCode);

    @Query("select d from Dock d where d.dockStatus.dockStatusId=1")
    List<Dock> findAvailableDock();
    
    @Query("select d from Dock d where d.dockStatus.dockStatusId=1 and d.harbor.harborCode=:harborCode")
    List<Dock> findDockByHarborCode(@Param("harborCode")String harborCode);

}
