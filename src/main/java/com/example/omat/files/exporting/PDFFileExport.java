package com.example.omat.files.exporting;

import com.example.omat.common.Month;
import com.example.omat.students.Group;
import com.example.omat.students.Student;

import java.util.ArrayList;

public class PDFFileExport extends CommonFileExport {
    public PDFFileExport(String exportDirectory) {
        super(exportDirectory);
    }

    @Override
    public void export(ArrayList<Group> groups, ArrayList<Student> students) {

    }
}
