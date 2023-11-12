package com.example.omat.files.exporting;

import com.example.omat.common.Month;
import com.example.omat.students.Group;
import com.example.omat.students.Student;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class EXCELFileExport extends CommonFileExport {
    public EXCELFileExport(String exportDirectory) {
        super(exportDirectory);
    }

    @Override
    public void export(ArrayList<Group> groups, ArrayList<Student> students) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Students");

            XSSFRow row0 = spreadsheet.createRow(0);
            row0.createCell(0).setCellValue("Student count");
            row0.createCell(1).setCellValue(students.size());

            XSSFRow row1 = spreadsheet.createRow(1);
            row1.createCell(0).setCellValue("ID");
            row1.createCell(1).setCellValue("Name");
            row1.createCell(2).setCellValue("Surname");
            row1.createCell(3).setCellValue("Faculty");
            int culumnOffset = 4;
            for(var month : Month.getMonths()) {
                row1.createCell(culumnOffset).setCellValue("Month");
                ++culumnOffset;

                for(int i = 0; i < month.getDays(); ++i) {
                    row1.createCell(culumnOffset).setCellValue("Day " + (i + 1));
                    ++culumnOffset;
                }
            }

            int rowOffset = 2;
            for(var st : students) {
                XSSFRow stRow = spreadsheet.createRow(rowOffset);
                stRow.createCell(0).setCellValue(st.getId());
                stRow.createCell(1).setCellValue(st.getName());
                stRow.createCell(2).setCellValue(st.getSurname());
                stRow.createCell(3).setCellValue(st.getFaculty().toString());

                int offset = 4;
                for(var month : Month.getMonths()) {
                    stRow.createCell(offset).setCellValue(month.toString());
                    ++offset;

                    for(int i = 0; i < month.getDays(); ++i) {
                        if(st.getAttendanceData().months.get(month).getDay(i)) {
                            stRow.createCell(offset).setCellValue("+");
                        } else
                            stRow.createCell(offset).setCellValue(" ");

                        ++offset;
                    }
                }

                ++rowOffset;
            }

            ++rowOffset;

            XSSFRow groupCountRow = spreadsheet.createRow(rowOffset);
            groupCountRow.createCell(0).setCellValue("Group count");
            groupCountRow.createCell(1).setCellValue(groups.size());
            ++rowOffset;

            XSSFRow groupsRow = spreadsheet.createRow(rowOffset);
            groupsRow.createCell(0).setCellValue("Name");
            groupsRow.createCell(1).setCellValue("Description");
            groupsRow.createCell(2).setCellValue("Student Count");
            for(int i = 3; i < findMaxStudentCountInGroups(groups) + 3; ++i) {
                groupsRow.createCell(i).setCellValue("Student " + (i - 2));
            }
            ++rowOffset;

            for(var group : groups) {
                XSSFRow groupRow = spreadsheet.createRow(rowOffset);
                groupRow.createCell(0).setCellValue(group.getName());
                groupRow.createCell(1).setCellValue(group.getDescription());
                groupRow.createCell(2).setCellValue(group.getStudents().size());
                int studentOffset = 3;

                for(var st : group.getStudents()) {
                    groupRow.createCell(studentOffset).setCellValue(getStudentIndex(st, students));
                    ++studentOffset;
                }

                ++rowOffset;
            }

            FileOutputStream out = new FileOutputStream(getTargetDirectory());
            workbook.write(out);
            out.close();
        } catch(Exception ex) {

        }
    }
}
