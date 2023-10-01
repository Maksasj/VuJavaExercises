package com.lab2.rkc.credit;

import com.lab2.rkc.ReplaymentScheduleType;
import com.lab2.rkc.PayData;

import java.util.List;

public abstract class Credit {
    private final String creditName;
    private final double creditAmount;
    private final double creditRate;
    private final int monthsToLast;

    public Credit() {
        this.creditName = "Unnamed credit";
        this.creditAmount = 0.0f;
        this.creditRate = 0.0f;
        this.monthsToLast = 0;
    }

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

    public abstract List<PayData> simulate();

    public abstract ReplaymentScheduleType getScheduleType();

    @Override
    public String toString() {
        return getCreditName() + " (" + getScheduleType().toString() + " kreditas)";
    }
}
