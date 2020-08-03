package com.enigma.finalproject.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.enigma.finalproject.entity.MasterRule;
public interface RuleRepository extends JpaRepository<MasterRule, Integer>{
	
	@Query("SELECT r from MasterRule r where r.id=:id")
	MasterRule findRuleById(@RequestParam("id") Integer id);
	List<MasterRule> findByOutputStatusOutputDiseaseIdOrderByIdAsc(Integer diseaseId);
}
