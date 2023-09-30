package com.lab2.lab2jfx;

import java.util.ArrayList;
import java.util.List;

public class AnnuityCredit extends Credit {
    public AnnuityCredit(String creditName, Double creditAmount, Double creditRate, Integer monthsToLast) {
        super(creditName, creditAmount, creditRate, monthsToLast);
    }

    public List<SinglePayData> simulate() {
        List<SinglePayData> paymentDataList = new ArrayList<>();

        double monthInterest = getCreditRate() / 12.0;

        double tmp = Math.pow(monthInterest + 1, getMonthsToLast());
        double balance = getCreditAmount();

        for (int i = 0; i < getMonthsToLast(); ++i) {
            double monthPayment = getCreditAmount() * monthInterest * tmp / (tmp - 1);
            monthPayment = Math.round(monthPayment * 100) / 100.0;

            double interest = balance * monthInterest;
            interest = Math.round(interest * 100) / 100.0;

            double credit = monthPayment - interest;
            credit = Math.round(credit * 100) / 100.0;

            SinglePayData paymentData = new SinglePayData(balance, monthPayment, interest, credit);
            paymentDataList.add(paymentData);

            balance -= credit;
            balance = Math.round(balance * 100) / 100.0;
        }

        return paymentDataList;
    }

    public ReplaymentScheduleType getScheduleType() {
        return ReplaymentScheduleType.ANNUITY;
    }
}
