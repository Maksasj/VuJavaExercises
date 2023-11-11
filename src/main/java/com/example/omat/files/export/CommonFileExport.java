package com.example.omat.files.export;

import com.example.omat.common.Month;
import com.example.omat.students.Student;

import java.util.ArrayList;

public abstract class CommonFileExport {
    public abstract void export(String filePath, Month month, ArrayList<Student> students);
}
