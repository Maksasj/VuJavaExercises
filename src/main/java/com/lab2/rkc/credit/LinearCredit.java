package com.lab2.rkc.credit;

import com.lab2.rkc.ReplaymentScheduleType;
import com.lab2.rkc.PayData;

import java.util.List;

public class LinearCredit extends Credit {
    public LinearCredit(String creditName, Double creditAmount, Double creditRate, Integer monthsToLast) {
        super(creditName, creditAmount, creditRate, monthsToLast);
    }

    @Override
    public List<PayData> simulate() {
        return null;
    }

    public ReplaymentScheduleType getScheduleType() {
        return ReplaymentScheduleType.LINEAR;
    }
}
