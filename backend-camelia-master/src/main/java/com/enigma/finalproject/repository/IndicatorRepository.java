package com.enigma.finalproject.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.enigma.finalproject.entity.Indicator;
public interface IndicatorRepository extends JpaRepository<Indicator, Integer>{
	List<Indicator> findByDiseaseId(Integer id);
	@Query("SELECT i from Indicator i where i.id=:id")
	Indicator findIndicatorById(@RequestParam("id") Integer id);
}
