package com.enigma.finalproject.fuzzy.membership;

public class FuzzyReverseGrade implements Membership {
	public double lowerValue;
	public double upperValue;

	public FuzzyReverseGrade(double x0, double x1) {
		this.lowerValue = x0;
		this.upperValue = x1;
	}

	public double degree(double x) {
		if (x <= lowerValue) {
			return 1;
		} else if (x < upperValue) {
			return (upperValue - x) / (upperValue - lowerValue);
		} else {
			return 0;
		}
	}
}
