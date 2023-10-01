package com.lab2.rkc.credit;

import com.lab2.rkc.PayData;
import com.lab2.rkc.ReplaymentScheduleType;

import java.util.ArrayList;
import java.util.List;

public abstract class Credit {
    private final String creditName;
    private final double creditAmount;
    private final double creditRate;
    private final int monthsToLast;
    private final List<Deferral> deferrals;

    public Credit() {
        this("Unnamed credit", 0.0, 0.0, 0);
    }

    public Credit(String creditName, Double creditAmount, Double creditRate, Integer monthsToLast) {
        deferrals = new ArrayList<>();

        this.creditName = creditName;
        this.creditAmount = creditAmount;
        this.creditRate = creditRate;
        this.monthsToLast = monthsToLast;
    }

    public Credit(String creditName, Double creditAmount, Double creditRate, Integer monthsToLast, List<Deferral> deferrals) {
        this.deferrals = deferrals;
        this.creditName = creditName;
        this.creditAmount = creditAmount;
        this.creditRate = creditRate;
        this.monthsToLast = monthsToLast;
    }

    public List<Deferral> getDeferrals() {
        return deferrals;
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

    public abstract List<PayData> simulate();

    public abstract ReplaymentScheduleType getScheduleType();

    @Override
    public String toString() {
        return getCreditName() + " (" + getScheduleType().toString() + " kreditas)";
    }
}
