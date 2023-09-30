package com.lab2.lab2jfx;

import java.util.ArrayList;
import java.util.List;

public abstract class Credit {
    private final String creditName;
    private final double creditAmount;
    private final double creditRate;
    private final int monthsToLast;

    public Credit(String creditName, Double creditAmount, Double creditRate, Integer monthsToLast) {
        this.creditName = creditName;
        this.creditAmount = creditAmount;
        this.creditRate = creditRate;
        this.monthsToLast = monthsToLast;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public double getCreditRate() {
        return creditRate;
    }

    public int getMonthsToLast() {
        return monthsToLast;
    }

    public String getCreditName() {
        return creditName;
    }

    public abstract List<SinglePayData> simulate();

    public abstract ReplaymentScheduleType getScheduleType();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getCreditName());
        sb.append(" (");
        sb.append(getScheduleType().toString());
        sb.append(" kreditas)");

        return sb.toString();
    }
}
