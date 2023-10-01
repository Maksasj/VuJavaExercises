package com.lab2.rkc.credit;

public class Deferral {
    private final String name;
    private final double rate;
    private final int start;
    private final int duration;

    public Deferral(String name, double rate, int start, int duration) {
        this.name = name;
        this.rate = rate;
        this.start = start;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public int getStart() {
        return start;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        String finishMonth = String.valueOf(getStart() + getDuration());
        return getName() + " (" + getStart() + "m - " + finishMonth +"m)";
    }
}
