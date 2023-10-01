package com.lab2.rkc;

public class PayData {
    private final double leftToPay;
    private final double monthPay;
    private final double interest;
    private final double credit;

    public PayData(double leftToPay, double monthPay, double interest, double credit) {
        this.leftToPay = leftToPay;
        this.monthPay = monthPay;
        this.interest = interest;
        this.credit = credit;
    }

    public PayData(PayData payData) {
        this.leftToPay = payData.getLeftToPay();
        this.monthPay = payData.getMonthPay();
        this.interest = payData.getInterest();
        this.credit = payData.getCredit();
    }

    public double getLeftToPay() {
        return leftToPay;
    }

    public double getMonthPay() {
        return monthPay;
    }

    public double getInterest() {
        return interest;
    }

    public double getCredit() {
        return credit;
    }
}
