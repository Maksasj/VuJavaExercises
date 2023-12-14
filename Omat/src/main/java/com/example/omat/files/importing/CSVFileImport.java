/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.example.omat.files.importing;

import com.example.omat.common.Month;
import com.example.omat.students.Faculty;
import com.example.omat.students.Group;
import com.example.omat.students.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CSVFileImport extends CommonFileImport {
    public CSVFileImport(String importDirectory) {
        super(importDirectory);
    }

    @Override
    public void importt(ArrayList<Group> groups, ArrayList<Student> students) {
        var studentsToAdd = new ArrayList<Student>();
        var groupsToAdd = new ArrayList<Group>();

        try {
            FileReader fileReader = new FileReader(getTargetDirectory());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            var allLines = new ArrayList<String[]>();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                var data = line.split(",");
                allLines.add(data);
            }

            int studentCount = Integer.parseInt(allLines.get(0)[1]);
            for(int i = 0; i < studentCount; ++i) {
                var studentData = allLines.get(2 + i);

                int id = Integer.parseInt(studentData[0]);
                String name = studentData[1];
                String surname = studentData[2];
                Faculty faculty = Faculty.valueOf(studentData[3]);

                Student student = new Student(id, name, surname, faculty);

                int offset = 0;
                for(int m = 0; m < 12; ++m) {
                    Month month = Month.getMonths().get(m);
                    ++offset;

                    for(int d = 0; d < month.getDays(); ++d) {
                        String data = studentData[4 + offset];
                        if(!data.contentEquals(" "))
                            student.getAttendanceData().months.get(month).markDay(d, true);

                        ++offset;
                    }
                }

                studentsToAdd.add(student);
            }

            int groupOffset = studentCount + 2 + 1;
            int groupCount = Integer.parseInt(allLines.get(groupOffset)[1]);
            for(int i = 0; i < groupCount; ++i) {
                var groupData = allLines.get(groupOffset + 2 + i);

                String name = groupData[0];
                String description = groupData[1];
                int studentCountInGroup = Integer.parseInt(groupData[2]);

                Group group = new Group(name, description);

                for(int s = 0; s < studentCountInGroup; ++s) {
                    int studentIndex = Integer.parseInt(groupData[3 + s]);
                    group.addStudent(studentsToAdd.get(studentIndex));
                }

                groupsToAdd.add(group);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        groups.addAll(groupsToAdd);
        students.addAll(studentsToAdd);
    }
}
