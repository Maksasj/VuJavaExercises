package com.example.omat.common;

import java.util.ArrayList;

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

    static private final ArrayList<Month> months;

    static {
        months = new ArrayList<>();

        months.add(Month.JANUARY);
        months.add(Month.FEBRUARY);
        months.add(Month.MARCH);
        months.add(Month.APRIL);
        months.add(Month.MAY);
        months.add(Month.JUNE);
        months.add(Month.JULY);
        months.add(Month.AUGUST);
        months.add(Month.SEPTEMBER);
        months.add(Month.OCTOBER);
        months.add(Month.NOVEMBER);
        months.add(Month.DECEMBER);
    }

    public static ArrayList<Month> getMonths() {
        return months;
    }
}
