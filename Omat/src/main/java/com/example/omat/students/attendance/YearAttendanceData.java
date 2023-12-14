/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.example.omat.students.attendance;

import com.example.omat.common.Month;

import java.util.HashMap;

public class YearAttendanceData {
    public HashMap<Month, MonthAttendanceData> months;

    public YearAttendanceData() {
        months = new HashMap<>();

        months.put(Month.JANUARY, new MonthAttendanceData(Month.JANUARY));
        months.put(Month.FEBRUARY, new MonthAttendanceData(Month.FEBRUARY));
        months.put(Month.MARCH, new MonthAttendanceData(Month.MARCH));
        months.put(Month.APRIL, new MonthAttendanceData(Month.APRIL));
        months.put(Month.MAY, new MonthAttendanceData(Month.MAY));
        months.put(Month.JUNE, new MonthAttendanceData(Month.JUNE));
        months.put(Month.JULY, new MonthAttendanceData(Month.JULY));
        months.put(Month.AUGUST, new MonthAttendanceData(Month.AUGUST));
        months.put(Month.SEPTEMBER, new MonthAttendanceData(Month.SEPTEMBER));
        months.put(Month.OCTOBER, new MonthAttendanceData(Month.OCTOBER));
        months.put(Month.NOVEMBER, new MonthAttendanceData(Month.NOVEMBER));
        months.put(Month.DECEMBER, new MonthAttendanceData(Month.DECEMBER));
    }
}
