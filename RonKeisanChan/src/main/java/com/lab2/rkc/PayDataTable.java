/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.lab2.rkc;

public class PayDataTable extends PayData {
    private final int month;

    public PayDataTable(int month, PayData payData) {
        super(payData);

        this.month = month;
    }

    public PayDataTable(int month, double leftToPay, double monthPay, double interest, double credit) {
        super(leftToPay, monthPay, interest, credit);

        this.month = month;
    }

    public int getMonth() {
        return month;
    }
}
