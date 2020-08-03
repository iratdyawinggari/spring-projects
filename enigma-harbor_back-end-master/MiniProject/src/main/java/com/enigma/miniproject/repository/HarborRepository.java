package com.enigma.miniproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enigma.miniproject.entity.Dock;
import com.enigma.miniproject.entity.Harbor;

public interface HarborRepository extends JpaRepository<Harbor, String> {
	@Query("select h from Harbor h where h.harborCode=:harborCode")
	Harbor findharborByharborCode(@Param("harborCode") String harborCode);

	@Query("select h from Harbor h where h.harborStatus.harborStatusId between 1 and 2")
	List<Harbor> findWithoutSuspend();
	
    @Query(value="SELECT COUNT(*) from ms_harbor h \r\n" + 
    		"INNER JOIN ms_dock d ON d.harbor_code = h.harbor_code \r\n" + 
    		"INNER JOIN ms_dock_status ds ON d.dock_status_id = ds.dock_status_id\r\n" + 
    		"WHERE ds.dock_status_id=1 and h.harbor_code=:harborCode\r\n", nativeQuery = true)
    Integer findCapacityHarbor(@Param("harborCode")String harborCode);
}
