package com.example.omat.files.export;

import com.example.omat.common.Month;
import com.example.omat.files.export.CommonFileExport;
import com.example.omat.students.Student;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class CSVFileExport extends CommonFileExport {
    public void export(String filePath, Month month, ArrayList<Student> students) {
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(filePath);

                fileWriter.write("ID,Name,Surname,Faculty,");

                for(int i = 0; i < month.getDays(); ++i) {
                    fileWriter.write("Day " + (i + 1) + ",");
                }

                fileWriter.write("\n");

                for(var st : students) {
                    fileWriter.write(st.getId() + ",");
                    fileWriter.write(st.getName() + ",");
                    fileWriter.write(st.getSurname() + ",");
                    fileWriter.write(st.getFaculty() + ",");

                    for(int i = 0; i < month.getDays(); ++i) {

                        if(st.getAttendanceData().months.get(month).getDay(i)) {
                            fileWriter.write("+,");
                        } else
                            fileWriter.write(" ,");
                    }

                    fileWriter.write("\n");
                }

                fileWriter.close();
            }
        } catch (Exception ignored) {

        }
    }
}
