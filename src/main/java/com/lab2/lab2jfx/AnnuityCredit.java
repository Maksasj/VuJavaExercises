package com.lab2.lab2jfx;

public class AnnuityCredit extends Credit {
    public AnnuityCredit(String creditName, Double creditAmount, Double creditRate, Integer monthsToLast) {
        super(creditName, creditAmount, creditRate, monthsToLast);
    }

    public ReplaymentScheduleType getScheduleType() {
        return ReplaymentScheduleType.ANNUITY;
    }
}
