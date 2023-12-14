/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

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
