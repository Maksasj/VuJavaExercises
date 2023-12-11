package com.example.tableviewer.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Utils {
    public static String[] parseCSV(String csv) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();

        for (char c : csv.toCharArray()) {
            if (c == '\"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        result.add(sb.toString());

        return result.toArray(new String[0]);
    }

    public static boolean inRange(int value, int min, int max) {
        if(value < min) return false;
        if(value > max) return false;

        return true;
    }

    public static boolean inRange(LocalDate value, LocalDate min, LocalDate max) {
        if(value.isAfter(max)) return false;
        if(value.isBefore(min)) return false;

        return true;
    }
}
