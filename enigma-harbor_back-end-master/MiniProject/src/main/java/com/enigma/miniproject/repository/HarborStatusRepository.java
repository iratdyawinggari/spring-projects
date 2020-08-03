package com.enigma.miniproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.enigma.miniproject.entity.HarborStatus;
import com.enigma.miniproject.entity.ShipStatus;

public interface HarborStatusRepository extends JpaRepository<HarborStatus, Integer>{
    @Query("select hs from HarborStatus hs where hs.harborStatusId=:harborStatusId")
    	HarborStatus findByHarborStatusId(@Param("harborStatusId")Integer harborStatusId);
    
	@Query("select hs from HarborStatus hs where hs.harborStatusId between 1 and 2")
		List<HarborStatus> findWithoutSuspend();
}
