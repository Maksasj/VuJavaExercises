/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.example.omat.files.exporting;

import com.example.omat.common.Month;
import com.example.omat.students.Group;
import com.example.omat.students.Student;

import java.util.ArrayList;
import java.util.Objects;

public abstract class CommonFileExport {
    private final String exportDirectory;
    private final Object extraData;

    public CommonFileExport(String exportDirectory, Object extraData) {
        this.exportDirectory = exportDirectory;
        this.extraData = extraData;
    }

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
    protected Object getExtraData() { return extraData; }

    protected String getTargetDirectory() {
        return exportDirectory;
    }

    public abstract void export(ArrayList<Group> groups, ArrayList<Student> students);
}
