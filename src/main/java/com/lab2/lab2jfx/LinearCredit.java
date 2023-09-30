package com.lab2.lab2jfx;

public class LinearCredit extends Credit {
    public LinearCredit(String creditName, Double creditAmount, Double creditRate, Integer monthsToLast) {
        super(creditName, creditAmount, creditRate, monthsToLast);
    }

    public ReplaymentScheduleType getScheduleType() {
        return ReplaymentScheduleType.LINEAR;
    }
}
