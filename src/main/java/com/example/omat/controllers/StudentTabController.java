package com.example.omat.controllers;

import com.example.omat.Omat;
import com.example.omat.OmatApplication;
import com.example.omat.common.CommonController;
import com.example.omat.students.Faculty;
import com.example.omat.students.Group;
import com.example.omat.students.Student;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StudentTabController extends CommonController {
    @FXML private Spinner<Integer> studentIDSpinner;
    @FXML private TextField studentSurnameTextField;
    @FXML private TextField studentNameTextField;
    @FXML private ChoiceBox<Faculty> studentFacultyChoiceBox;
    @FXML private ChoiceBox<Group> studentGroupChoiceBox;

    @FXML private ListView<Student> studentsListView;

    @Override
    public void onAnyUpdate() {
        var studentItems = FXCollections.observableArrayList(Omat.getStudents());
        studentsListView.setItems(studentItems);

        var groupItems = FXCollections.observableArrayList(Omat.getGroups());
        studentGroupChoiceBox.setItems(groupItems);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        setIntegerValueFactory(studentIDSpinner, 0, Integer.MAX_VALUE);

        studentFacultyChoiceBox.getItems().addAll(
            Faculty.MATH_INFORMATICS,
            Faculty.BUSINESS_SCHOOL,
            Faculty.CHEMISTRY_GEOSCIENCES,
            Faculty.COMMUNICATION,
            Faculty.ECONOMICS_BUSINESS_ADMINISTRATION,
            Faculty.HISTORY,
            Faculty.LAW,
            Faculty.MEDICINE,
            Faculty.PHILOLOGY,
            Faculty.PHILOSOPHY,
            Faculty.PHYSICS
        );
        studentFacultyChoiceBox.getSelectionModel().select(0);

        OmatApplication.onAnyUpdate();
    }

    public void notifyError(String error) {
        // Todo
    }

    @FXML
    protected void onAddStudent() {
        int studentId = 0;
        String studentName = "";
        String studentSurname = "";
        Group selectedGroup = null;
        Faculty studentFaculty = Faculty.MATH_INFORMATICS; // As a default

        {
            var value = studentIDSpinner.getValue();
            if(value != null)
                studentId = value;
            else {
                notifyError("Student Id is not provided");
                return;
            }
        }

        {
            var value = studentNameTextField.getText();
            if(value != null && !value.contentEquals(""))
                studentName = value;
            else {
                notifyError("Student name could not be empty");
                return;
            }
        }

        {
            var value = studentSurnameTextField.getText();
            if(value != null && !value.contentEquals(""))
                studentSurname = value;
            else {
                notifyError("Student surname could not be empty");
                return;
            }
        }

        {
            var value = studentFacultyChoiceBox.getValue();
            if(value != null)
                studentFaculty = value;
            else {
                notifyError("Student faculty is not selected");
                return;
            }
        }

        {
            var value = studentGroupChoiceBox.getValue();
            if(value != null)
                selectedGroup = value;
            else {
                notifyError("Group is not selected");
                return;
            }
        }

        Student student = new Student(studentId, studentName, studentSurname, studentFaculty);
        Omat.addStudent(student);
        selectedGroup.addStudent(student);

        OmatApplication.onAnyUpdate();
    }

    @FXML
    protected void onEditSelectedStudent() {

        OmatApplication.onAnyUpdate();
    }

    @FXML
    protected void onDeleteSelectedStudent() {

        OmatApplication.onAnyUpdate();
    }


    @FXML
    protected void onDeleteAllStudents() {
        Omat.deleteAllStudents();

        OmatApplication.onAnyUpdate();
    }
}
