package com.example.omat.files.importing;

import com.example.omat.common.Month;
import com.example.omat.students.Group;
import com.example.omat.students.Student;

import java.util.ArrayList;

public abstract class CommonFileImport {
    private final String importDirectory;

    public CommonFileImport(String importDirectory) {
        this.importDirectory = importDirectory;
    }

    protected String getTargetDirectory() {
        return importDirectory;
    }

    public abstract void importt(ArrayList<Group> groups, ArrayList<Student> students);
}
