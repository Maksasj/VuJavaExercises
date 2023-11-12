package com.example.omat.files.exporting;

import com.example.omat.common.Month;
import com.example.omat.students.Group;
import com.example.omat.students.Student;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;

public class CSVFileExport extends CommonFileExport {
    public CSVFileExport(String exportDirectory, Object extraData) {
        super(exportDirectory, extraData);
    }

    @Override
    public void export(ArrayList<Group> groups, ArrayList<Student> students) {
        try {
            File file = new File(getTargetDirectory());
            if (file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(getTargetDirectory());

                fileWriter.write("Student count," + students.size());
                fileWriter.write("\n");
                fileWriter.write("ID,Name,Surname,Faculty,");

                for(var month : Month.getMonths()) {
                    fileWriter.write("Month,");

                    for(int i = 0; i < month.getDays(); ++i) {
                        fileWriter.write("Day " + (i + 1) + ",");
                    }
                }

                fileWriter.write("\n");

                for(var st : students) {
                    fileWriter.write(st.getId() + ",");
                    fileWriter.write(st.getName() + ",");
                    fileWriter.write(st.getSurname() + ",");
                    fileWriter.write(st.getFaculty() + ",");

                    for(var month : Month.getMonths()) {
                        fileWriter.write(month.toString() + ",");

                        for(int i = 0; i < month.getDays(); ++i) {

                            if(st.getAttendanceData().months.get(month).getDay(i)) {
                                fileWriter.write("+,");
                            } else
                                fileWriter.write(" ,");
                        }
                    }

                    fileWriter.write("\n");
                }
                fileWriter.write("\n");

                fileWriter.write("Group count," + groups.size());
                fileWriter.write("\n");
                fileWriter.write("Name,Description,Student Count,");

                int maxStudentsInGroup = findMaxStudentCountInGroups(groups);
                for(int i = 0; i < maxStudentsInGroup; ++i) {
                    fileWriter.write("Student " + (i + 1) + ",");
                }
                fileWriter.write("\n");

                for(var group : groups) {
                    fileWriter.write(group.getName() + ",");
                    fileWriter.write(group.getDescription() + ",");
                    fileWriter.write(group.getStudents().size() + ",");

                    for(var st : group.getStudents()) {
                        fileWriter.write(getStudentIndex(st, students) + ",");
                    }
                    fileWriter.write("\n");
                }


                fileWriter.close();
            }
        } catch (Exception ignored) {

        }
    }
}
