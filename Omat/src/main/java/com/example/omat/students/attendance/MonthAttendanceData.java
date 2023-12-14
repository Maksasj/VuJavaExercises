/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.example.omat.students.attendance;

import com.example.omat.common.Month;
import javafx.scene.control.cell.CheckBoxListCell;

import java.util.ArrayList;

public class MonthAttendanceData {
    private Month month;
    private boolean[] days;

    public MonthAttendanceData(Month month) {
        days = new boolean[month.getDays()];

        for(var day : days)
            day = false;
    }

    public boolean getDay(int day) {
        return days[day];
    }

    public void markDay(int day, boolean value) {
        days[day] = value;
    }

    public int getDayCount() {
        return days.length;
    }
}
