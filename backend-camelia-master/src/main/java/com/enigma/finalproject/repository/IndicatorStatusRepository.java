package com.enigma.finalproject.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.enigma.finalproject.entity.IndicatorStatus;

public interface IndicatorStatusRepository extends JpaRepository<IndicatorStatus, Integer> {
	List<IndicatorStatus> findByIndicatorDiseaseId(Integer id);

	List<IndicatorStatus> findByIndicatorId(Integer indicatorId);

	@Query("SELECT i from IndicatorStatus i where i.id=:id")
	IndicatorStatus findIndicatorStatusById(@RequestParam("id") Integer id);

}
