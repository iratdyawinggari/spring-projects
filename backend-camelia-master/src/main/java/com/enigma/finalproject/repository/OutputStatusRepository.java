package com.enigma.finalproject.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.enigma.finalproject.entity.OutputStatus;

public interface OutputStatusRepository extends JpaRepository<OutputStatus, Integer> {
	List<OutputStatus> findByOutputDiseaseId(Integer id);

	@Query("SELECT o from OutputStatus o where o.id=:id")
	OutputStatus findOutputStatusById(@RequestParam("id") Integer id);

}
