package com.lab2.lab2jfx;

public abstract class Credit {
    private final String creditName;
    private final Double creditAmount;
    private final Double creditRate;
    private final Integer monthsToLast;

    public Credit(String creditName, Double creditAmount, Double creditRate, Integer monthsToLast) {
        this.creditName = creditName;
        this.creditAmount = creditAmount;
        this.creditRate = creditRate;
        this.monthsToLast = monthsToLast;
    }

    public String getCreditName() {
        return creditName;
    }

    public abstract ReplaymentScheduleType getScheduleType();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getCreditName());
        sb.append(" (");
        sb.append(getScheduleType().toString());
        sb.append(" kreditas)");

        return sb.toString();
    }
}
