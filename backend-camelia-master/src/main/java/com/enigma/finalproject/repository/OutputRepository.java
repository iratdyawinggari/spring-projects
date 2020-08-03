package com.enigma.finalproject.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.enigma.finalproject.entity.Output;

public interface OutputRepository extends JpaRepository<Output, Integer> {
	Output findByDiseaseId(Integer id);

	@Query("SELECT o from Output o where o.id=:id")
	Output findOutputById(@RequestParam("id") Integer id);
}
