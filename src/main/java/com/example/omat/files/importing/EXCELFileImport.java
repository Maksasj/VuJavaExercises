package com.example.omat.files.importing;

import com.example.omat.common.Month;
import com.example.omat.students.Faculty;
import com.example.omat.students.Group;
import com.example.omat.students.Student;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class EXCELFileImport extends CommonFileImport {
    public EXCELFileImport(String importDirectory) {
        super(importDirectory);
    }

    private String getStringCellValue(XSSFSheet sheet, int row, int col) {
        return sheet.getRow(row).getCell(col).getStringCellValue();
    }

    private int getIntCellValue(XSSFSheet sheet, int row, int col) {
        return (int) sheet.getRow(row).getCell(col).getNumericCellValue();
    }

    @Override
    public void importt(ArrayList<Group> groups, ArrayList<Student> students) {
        var groupsToAdd = new ArrayList<Group>();
        var studentsToAdd = new ArrayList<Student>();

        try {
            InputStream stream = new FileInputStream(getTargetDirectory());
            XSSFWorkbook workbook = new XSSFWorkbook(stream);
            XSSFSheet spreadsheet = workbook.getSheetAt(0);

            int studentCount = getIntCellValue(spreadsheet, 0, 1);
            int rowOffset = 2;

            for(int i = 0; i < studentCount; ++i) {
                int id = getIntCellValue(spreadsheet, rowOffset, 0);
                String name = getStringCellValue(spreadsheet, rowOffset, 1);
                String surname = getStringCellValue(spreadsheet, rowOffset, 2);
                Faculty faculty = Faculty.valueOf(getStringCellValue(spreadsheet, rowOffset, 3));

                Student student = new Student(id, name, surname, faculty);

                int offset = 0;
                for(int m = 0; m < 12; ++m) {
                    Month month = Month.getMonths().get(m);
                    ++offset;

                    for(int d = 0; d < month.getDays(); ++d) {
                        String data = getStringCellValue(spreadsheet, rowOffset, 4 + offset);

                        if(!data.contentEquals(" "))
                            student.getAttendanceData().months.get(month).markDay(d, true);

                        ++offset;
                    }
                }

                studentsToAdd.add(student);

                ++rowOffset;
            }
            ++rowOffset;
            int groupCount = getIntCellValue(spreadsheet, rowOffset, 1);
            rowOffset += 2;

            for(int i = 0 ;i < groupCount; ++i) {
                String name = getStringCellValue(spreadsheet, rowOffset, 0);
                String description = getStringCellValue(spreadsheet, rowOffset, 1);

                var group = new Group(name, description);

                int groupStudentCount = getIntCellValue(spreadsheet, rowOffset, 2);

                for(int s = 0; s < groupStudentCount; ++s) {
                    int studentIndex = getIntCellValue(spreadsheet, rowOffset, 3 + s);
                    group.addStudent(studentsToAdd.get(studentIndex));
                }

                groupsToAdd.add(group);
                ++rowOffset;
            }

            stream.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        groups.addAll(groupsToAdd);
        students.addAll(studentsToAdd);
    }
}
