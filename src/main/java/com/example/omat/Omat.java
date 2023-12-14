/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.example.omat;

import com.example.omat.students.Group;
import com.example.omat.students.Student;

import java.util.ArrayList;

public class Omat {
    static private ArrayList<Student> students = new ArrayList<>();
    static private ArrayList<Group> groups = new ArrayList<>();

    public static ArrayList<Student> getStudents() {
        return students;
    }

    public static ArrayList<Group> getGroups() {
        return groups;
    }

    public static void addStudent(Student student) {
        students.add(student);
    }

    public static void addGroup(Group group) {
        groups.add(group);
    }

    public static void deleteStudent(Student student) {
        students.remove(student);
    }

    public static void onAnyUpdate() {
        for(var group : groups) {
            var toDelete = new ArrayList<>();

            for(var st : group.getStudents()) {
                if(!students.contains(st)) {
                    toDelete.add(st);
                }
            }

            group.getStudents().removeAll(toDelete);
        }
    }

    public static void deleteAllStudents() {
        students.clear();
    }
    public static void deleteAllGroups() {
        groups.clear();
    }

    public static void deleteGroup(Group group) {
        groups.remove(group);
    }
}
