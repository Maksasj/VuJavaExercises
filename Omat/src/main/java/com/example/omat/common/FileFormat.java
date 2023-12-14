/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.example.omat.common;

public enum FileFormat {
    CSV,
    EXCEL,
    PDF;

    public String getExtension() {
        return switch (this) {
            case CSV -> "csv";
            case EXCEL -> "xlsx";
            case PDF -> "pdf";
        };
    }
}
