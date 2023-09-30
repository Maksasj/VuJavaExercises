package com.lab2.lab2jfx;

public class Credit {
    private String name;
    private float amount;
    private float rate;

    public Credit() {
        this("Unnamed credit", 0.0f, 0.0f);
    }

    public Credit(String name, float amount, float rate) {
        this.name = name;
        this.amount = amount;
        this.rate = rate;

        this.printName();
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getAmount() {
        return amount;
    }

    public float getRate() {
        return rate;
    }

    public void printName() {
        System.out.println("Hello I'am a Credit !");
    }

    @Override
    public String toString() {
        return
            "Credit{ " +
                "name = " + name + ", " +
                "amount = " + amount + ", " +
                "rate = " + rate + ", " +
            "}";
    }
}
