package com.enigma.finalproject.fuzzy.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleInferenceEngine {
    List<String> mVariables = new ArrayList<String>();
    Map<String, FuzzySet> mWorkingMemory = new HashMap<String, FuzzySet>();
    List<Rule> mRules = new ArrayList<Rule>();

    public void addFuzzySet(String variable, FuzzySet set) {
        mVariables.add(variable);
        mWorkingMemory.put(variable, set);
    }

    public int getFuzzySetCount() {
        return mVariables.size();
    }

    public void addRule(Rule fr) {
        mRules.add(fr);
    }

    public void setVariable(String variable, double crispValue) {
        mWorkingMemory.get(variable).setX(crispValue);
    }

    public double getVariable(String variable){
        return mWorkingMemory.get(variable).getX();
    }

    public void Infer(FuzzySet output){
        output.fire(mRules);
    }
}
