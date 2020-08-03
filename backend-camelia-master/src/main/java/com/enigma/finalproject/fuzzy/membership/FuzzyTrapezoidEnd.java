package com.enigma.finalproject.fuzzy.membership;

public class FuzzyTrapezoidEnd implements Membership{
    public double x0;
    public double x1;
    public double x2;

    public double getX0() {
        return x0;
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public FuzzyTrapezoidEnd(double x0, double x1, double x2){
        this.x0 = x0;
        this.x1 = x1;
        this.x2 = x2;
    }

    public double degree(double x) {
        if (x <= x1 && x >= x0) {
            return (x-x0)/(x1-x0);
        }
        else if (x >= x1 && x <= x2) {
            return 1;
        }
        else {
            return 0;
        }
    }

}
