package com.lab2.rkc.credit;

import com.lab2.rkc.PayData;
import com.lab2.rkc.ReplaymentScheduleType;
import com.lab2.rkc.Utils;

import java.util.ArrayList;
import java.util.List;

public class LinearCredit extends Credit {
    public LinearCredit(String creditName, Double creditAmount, Double creditRate, Integer monthsToLast, List<Deferral> deferrals) {
        super(creditName, creditAmount, creditRate, monthsToLast, deferrals);
    }

    @Override
    public List<PayData> simulate() {
        List<PayData> paymentDataList = new ArrayList<>();

        double monthInterest = getCreditRate() / 12.0;
        double balance = getCreditAmount();

        for (int i = 0; i < getMonthsToLast(); ++i) {
            int daysInMonth = Utils.DAYS_IN_MONTH[i % 12];
            double monthPayment = balance / (getMonthsToLast() - i) + balance * getCreditRate() * daysInMonth / Utils.DAYS_IN_YEAR;
            monthPayment = Math.round(monthPayment * 100) / 100.0;

            double interest = balance * monthInterest;
            interest = Math.round(interest * 100) / 100.0;

            double credit = monthPayment - interest;
            credit = Math.round(credit * 100) / 100.0;

            PayData paymentData = new PayData(balance, monthPayment, interest, credit);
            paymentDataList.add(paymentData);

            balance -= credit;
            balance = Math.round(balance * 100) / 100.0;
        }

        return paymentDataList;
    }

    public ReplaymentScheduleType getScheduleType() {
        return ReplaymentScheduleType.LINEAR;
    }
}
