package com.company;

public class BottomNode extends Node{

    private int devYear = 0;
    private double value;

    public BottomNode(int devYear, double value) {
        this.devYear = devYear;
        this.value = value;
    }

    public int getDevYear() {
        return devYear;
    }

    public void setDevYear(int devYear) {
        this.devYear = devYear;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
