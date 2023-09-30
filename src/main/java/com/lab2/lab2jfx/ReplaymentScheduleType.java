package com.lab2.lab2jfx;

public enum ReplaymentScheduleType {
    LINEAR,
    ANNUITY;

    @Override
    public String toString() {
        return switch (this) {
            case LINEAR -> "Linijinis";
            case ANNUITY -> "Anuiteto";
        };
    }
}
