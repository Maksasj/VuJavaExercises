package com.lab2.lab2jfx;

public class Bank {
    private int money;

    public Bank(int startMoney) {
        money = startMoney;
    }

    public void addDebth(int money) {
        this.money = money;
    }
}
