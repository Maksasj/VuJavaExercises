package com.example.omat.students;

public class Student {
    private int id;
    private String name;
    private String surname;
    private Faculty faculty;

    public Student(int id, String name, String surname, Faculty faculty) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.faculty = faculty;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Faculty getFaculty() {
        return faculty;
    }
}
