package com.lab2.lab2jfx;

import java.util.List;

public class LinearCredit extends Credit {
    public LinearCredit(String creditName, Double creditAmount, Double creditRate, Integer monthsToLast) {
        super(creditName, creditAmount, creditRate, monthsToLast);
    }

    @Override
    public List<SinglePayData> simulate() {
        return null;
    }

    public ReplaymentScheduleType getScheduleType() {
        return ReplaymentScheduleType.LINEAR;
    }
}
