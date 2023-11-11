package com.example.omat.files.exporting;

import com.example.omat.common.Month;
import com.example.omat.students.Group;
import com.example.omat.students.Student;

import java.util.ArrayList;

public abstract class CommonFileExport {
    private final String exportDirectory;

    protected int getStudentIndex(Student student, ArrayList<Student> students) {
        for(int i = 0; i < students.size(); ++i) {
            if(students.get(i) == student) {
                return i;
            }
        }

        return -1;
    }

    protected int findMaxStudentCountInGroups(ArrayList<Group> groups) {
        int i = 0;

        for(var g : groups) {
            if(g.getStudents().size() > i) {
                i = g.getStudents().size();
            }
        }

        return i;
    }

    public CommonFileExport(String exportDirectory) {
        this.exportDirectory = exportDirectory;
    }

    protected String getTargetDirectory() {
        return exportDirectory;
    }

    public abstract void export(ArrayList<Group> groups, ArrayList<Student> students);
}
