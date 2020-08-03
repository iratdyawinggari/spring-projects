package com.enigma.finalproject.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.MasterRule;
import com.enigma.finalproject.repository.RuleRepository;
@Service
public class MasterRuleService {
	@Autowired
	private RuleRepository ruleRepository;

	public List<MasterRule> getAllRules() {
		return ruleRepository.findAll();
	}

	public List<MasterRule> getMasterRuleByDiseaseId(Integer diseaseId) {
		return ruleRepository.findByOutputStatusOutputDiseaseIdOrderByIdAsc(diseaseId);
	}

	public MasterRule getMasterRuleById(Integer id) {
		return ruleRepository.findRuleById(id);
	}

	public MasterRule editMasterRule(MasterRule masterRule) {
		return ruleRepository.save(masterRule);
	}

	public MasterRule addMasterRule(MasterRule masterRule) {
		return ruleRepository.save(masterRule);
	}
}
