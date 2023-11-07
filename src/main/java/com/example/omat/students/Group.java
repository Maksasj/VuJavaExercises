package com.example.omat.students;

import java.util.ArrayList;

public class Group {
    private String name;
    private String description;
    private final ArrayList<Student> students;

    public Group(String name, String description) {
        students = new ArrayList<>();

        this.name = name;
        this.description = description;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
