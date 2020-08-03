package com.enigma.finalproject.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.Indicator;
import com.enigma.finalproject.entity.IndicatorStatus;
import com.enigma.finalproject.entity.MasterRule;
import com.enigma.finalproject.entity.Output;
import com.enigma.finalproject.entity.OutputStatus;
import com.enigma.finalproject.entity.Transaction;
import com.enigma.finalproject.fuzzy.entity.Clause;
import com.enigma.finalproject.fuzzy.entity.FuzzySet;
import com.enigma.finalproject.fuzzy.entity.Rule;
import com.enigma.finalproject.fuzzy.entity.RuleInferenceEngine;
import com.enigma.finalproject.fuzzy.membership.FuzzyTrapezoid;
import com.enigma.finalproject.fuzzy.membership.FuzzyTrapezoidBegin;
import com.enigma.finalproject.fuzzy.membership.FuzzyTrapezoidEnd;

@Service
public class FuzzyProcessService {

	@Autowired
	private IndicatorService indicatorService;

	@Autowired
	private OutputService outputService;

	@Autowired
	private OutputStatusService outputStatusService;

	@Autowired
	private MasterRuleService masterRuleService;

	@Autowired
	private IndicatorStatusService indicatorStatusService;

	public void process(Transaction transaction) {
		RuleInferenceEngine rie = new RuleInferenceEngine();
		List<Indicator> indicators = indicatorService.getIndicatorByDiseaseId(transaction.getDisease().getId());
		List<List<Integer>> intervalPoints = new ArrayList<List<Integer>>();

		Output output = outputService.getOutputByDiseaseId(transaction.getDisease().getId());

		List<OutputStatus> outputStatuses = outputStatusService
				.getOutputStatusByDiseaseId(transaction.getDisease().getId());

		List<Integer> outputInterval = new ArrayList<Integer>();
		for (OutputStatus outputStatus : outputStatuses) {
			outputInterval.add(outputStatus.getMinPoint());
			outputInterval.add(outputStatus.getMaxPoint());
		}
		Collections.sort(outputInterval);

		FuzzySet fuzzySetOutput = new FuzzySet(output.getOutputName(), outputInterval.get(0),
				outputInterval.get(outputInterval.size() - 1), 0.001);
		int l = 0;
		for (int j = 0; j < outputStatuses.size(); j++) {
			if (l == 0) {
				fuzzySetOutput.addMembership(outputStatuses.get(j).getOutputStatusName(),
						new FuzzyTrapezoidBegin(outputInterval.get(0), outputInterval.get(1), outputInterval.get(2)));
			} else if (outputInterval.size() - l == 3) {
				fuzzySetOutput.addMembership(outputStatuses.get(j).getOutputStatusName(), new FuzzyTrapezoidEnd(
						outputInterval.get(l), outputInterval.get(l + 1), outputInterval.get(l + 2)));
			} else if (l % 2 == 1) {
				fuzzySetOutput.addMembership(outputStatuses.get(j).getOutputStatusName(),
						new FuzzyTrapezoid(outputInterval.get(l), outputInterval.get(l + 1), outputInterval.get(l + 2),
								outputInterval.get(l + 3)));
				l++;
			}
			l++;
		}
		rie.addFuzzySet(fuzzySetOutput.getName(), fuzzySetOutput);

		List<FuzzySet> fuzzySets = new ArrayList<FuzzySet>();

		for (int i = 0; i < indicators.size(); i++) {
			List<Integer> integers = new ArrayList<Integer>();
			List<IndicatorStatus> indicatorStatuses = indicatorStatusService
					.getIndicatorStatusByIndicatorId(indicators.get(i).getId());
			;
			for (int j = 0; j < indicatorStatuses.size(); j++) {
				integers.add(indicatorStatuses.get(j).getMinPoint());
				integers.add(indicatorStatuses.get(j).getMaxPoint());
			}
			Collections.sort(integers);
			intervalPoints.add(integers);

			FuzzySet fuzzySet = new FuzzySet(indicators.get(i).getIndicatorName(), integers.get(0),
					integers.get(integers.size() - 1), 0.001);
			int k = 0;
			for (int j = 0; j < indicatorStatuses.size(); j++) {
				if (k == 0) {
					fuzzySet.addMembership(indicatorStatuses.get(j).getIndicatorStatusName(),
							new FuzzyTrapezoidBegin(integers.get(0), integers.get(1), integers.get(2)));
				} else if (integers.size() - k == 3) {
					fuzzySet.addMembership(indicatorStatuses.get(j).getIndicatorStatusName(),
							new FuzzyTrapezoidEnd(integers.get(k), integers.get(k + 1), integers.get(k + 2)));

				} else if (k % 2 == 1) {
					fuzzySet.addMembership(indicatorStatuses.get(j).getIndicatorStatusName(), new FuzzyTrapezoid(
							integers.get(k), integers.get(k + 1), integers.get(k + 2), integers.get(k + 3)));
					k++;
				}
				k++;
			}
			rie.addFuzzySet(fuzzySet.getName(), fuzzySet);
			fuzzySets.add(fuzzySet);
		}

		List<MasterRule> rules = masterRuleService.getMasterRuleByDiseaseId(transaction.getDisease().getId());
		List<Rule> rulessss = new ArrayList<Rule>();

		Rule rule = new Rule("Rule 1");
		int s = 2;
		for (MasterRule masterRule : rules) {
			String string = masterRule.getRuleCombination();
			String[] arrSplit = string.split(",");
			for (int i = 0; i < arrSplit.length; i++) {
				String[] ruleSplit = arrSplit[i].split(" ");
				for (int q = 0; q < indicators.size(); q++) {
					List<IndicatorStatus> indicatorStatuses = indicatorStatusService
							.getIndicatorStatusByIndicatorId(indicators.get(q).getId());
					for (int j = 0; j < indicatorStatuses.size(); j++) {

						if (ruleSplit[0].equalsIgnoreCase(fuzzySets.get(q).getName())
								&& ruleSplit[2].equalsIgnoreCase(fuzzySets.get(q).GetMembershipName(j))) {
							rule.addAntecedent(new Clause(fuzzySets.get(q), "Is", ruleSplit[2]));
						}
					}
				}
			}
			for (int j = 0; j < outputStatuses.size(); j++) {
				if (masterRule.getOutputStatus().getOutputStatusName()
						.equalsIgnoreCase(fuzzySetOutput.GetMembershipName(j))) {
					rule.setConsequent(
							new Clause(fuzzySetOutput, "Is", masterRule.getOutputStatus().getOutputStatusName()));
					;
				}
			}
			rie.addRule(rule);
			rulessss.add(rule);
			String d = "rule" + s;
			rule = new Rule(d);
			s++;
		}

		String input = transaction.getInputPointCombination();
		String[] inputSplit = input.split(",");
		for (int i = 0; i < inputSplit.length; i++) {
			String[] pointSplit = inputSplit[i].split(" ");
			for (int j = 0; j < fuzzySets.size(); j++) {
				if (pointSplit[0].equalsIgnoreCase(fuzzySets.get(j).getName())) {
					fuzzySets.get(j).setX(Double.valueOf(pointSplit[1]));
				}
			}
		}

		rie.Infer(fuzzySetOutput);

		transaction.setOutputPoint(fuzzySetOutput.getX());
		Map<String, Double> arrayDegree = new HashMap<String, Double>();
		for (int i = 0; i < outputStatuses.size(); i++) {
			String name = outputStatuses.get(i).getOutputStatusName();
			arrayDegree.put(name, fuzzySetOutput.GetMembership(name).degree(fuzzySetOutput.getX()));
		}

		Double maxValueInMap = (Collections.max(arrayDegree.values()));
		String criteria = "";
		for (Entry<String, Double> entry : arrayDegree.entrySet()) {
			if (entry.getValue() == maxValueInMap) {
				criteria = entry.getKey();
			}
		}

		for (int i = 0; i < outputStatuses.size(); i++) {
			if (outputStatuses.get(i).getOutputStatusName().equalsIgnoreCase(criteria)) {
				transaction.setOutputStatus(outputStatuses.get(i));
			}
		}
	}
}
