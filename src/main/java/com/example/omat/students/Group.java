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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return name;
    }
}
