package com.example.tableviewer.utils;

import java.util.ArrayList;
import java.util.List;

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
}
