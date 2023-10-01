package com.lab2.rkc;

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
