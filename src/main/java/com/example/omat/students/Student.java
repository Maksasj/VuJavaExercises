package com.example.omat.students;

import com.example.omat.students.attendance.YearAttendanceData;

import java.util.HashMap;

public class Student {
    private int id;
    private String name;
    private String surname;
    private Faculty faculty;
    private YearAttendanceData attendanceData;

    public Student(int id, String name, String surname, Faculty faculty) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.faculty = faculty;

        this.attendanceData = new YearAttendanceData();
    }

    public int getId() {
        return id;
    }

    public YearAttendanceData getAttendanceData() {
        return attendanceData;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
