package com.lab2.lab2jfx;

public class LongTermCredit extends Credit {
    private float duration;

    public LongTermCredit() {
        this("Unnamed long term credit", 0.0f, 0.0f, 0.0f);
    }

    public LongTermCredit(String name, float amount, float rate, float duration) {
        super(name, amount, rate);

        this.duration = duration;

        // this.printName();
        // super.printName();
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getDuration() {
        return duration;
    }

    public void printName() {
        System.out.println("Hello I'am a Long Term Credit !");
    }
}
