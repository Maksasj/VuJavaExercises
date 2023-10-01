package com.lab2.rkc.credit;

import com.lab2.rkc.PayData;
import com.lab2.rkc.ReplaymentScheduleType;

import java.util.ArrayList;
import java.util.List;

public class AnnuityCredit extends Credit {
    public AnnuityCredit(String creditName, Double creditAmount, Double creditRate, Integer monthsToLast, List<Deferral> deferrals) {
        super(creditName, creditAmount, creditRate, monthsToLast, deferrals);
    }

    public List<PayData> simulate() {
        List<PayData> paymentDataList = new ArrayList<>();

        double monthInterest = getCreditRate() / 12.0;

        double tmp = Math.pow(monthInterest + 1, getMonthsToLast());
        double balance = getCreditAmount();

        int monthsToLast = getMonthsToLast();

        for (int i = 0; i < monthsToLast; ++i) {
            for(var deferral : getDeferrals()) {
                if(i == deferral.getStart()) {
                    List<PayData> deferrals = new ArrayList<>();

                    double fixedInterest = deferral.getRate();

                    for(int j = 0; j < deferral.getDuration(); ++j) {
                        double interest = Math.round(balance * fixedInterest);
                        PayData payData = new PayData(balance, interest, interest, 0);
                        deferrals.add(payData);
                    }

                    paymentDataList.addAll(deferrals);

                    monthsToLast += deferral.getDuration();
                    i += deferral.getDuration();
                }
            }

            double monthPayment = getCreditAmount() * monthInterest * tmp / (tmp - 1);
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
        return ReplaymentScheduleType.ANNUITY;
    }
}
