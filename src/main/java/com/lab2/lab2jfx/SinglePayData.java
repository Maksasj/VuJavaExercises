package com.lab2.lab2jfx;

public class SinglePayData {
    private final double leftToPay;
    private final double monthPay;
    private final double interest;
    private final double credit;

    public SinglePayData(double leftToPay, double monthPay, double interest, double credit) {
        this.leftToPay = leftToPay;
        this.monthPay = monthPay;
        this.interest = interest;
        this.credit = credit;
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
