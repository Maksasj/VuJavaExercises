package com.example.omat.common;

public enum Month {
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER;

    public int getDays() {
        return switch (this) {
            case JANUARY -> 31;
            case FEBRUARY -> 28;
            case MARCH -> 31;
            case APRIL -> 30;
            case MAY -> 31;
            case JUNE -> 30;
            case JULY -> 31;
            case AUGUST -> 31;
            case SEPTEMBER -> 30;
            case OCTOBER -> 31;
            case NOVEMBER -> 30;
            case DECEMBER -> 31;
        };
    }
}
