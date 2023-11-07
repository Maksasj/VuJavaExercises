package com.example.omat.controllers;

import com.example.omat.Omat;
import com.example.omat.OmatApplication;
import com.example.omat.students.Faculty;
import com.example.omat.students.Student;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StudentTabController implements Initializable {
    @FXML private Spinner<Integer> studentIDSpinner;
    @FXML private TextField studentSurnameTextField;
    @FXML private TextField studentNameTextField;
    @FXML private ChoiceBox<Faculty> studentFacultyChoiceBox;

    @FXML private ListView<Student> studentsListView;

    private void setIntegerValueFactory(Spinner<Integer> spinner, int min, int max) {
        var valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max);
        valueFactory.setValue(min);
        spinner.setValueFactory(valueFactory);

        spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            spinner.getValueFactory().setValue(newValue);
        });

        onAnyUpdate();
    }

    public void onAnyUpdate() {
        fillListView();
    }

    public void fillListView() {
        var items = FXCollections.observableArrayList(Omat.getStudents());
        studentsListView.setItems(items);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    }

    public void notifyError(String error) {
        // Todo
    }

    @FXML
    protected void onAddStudent() {
        int studentId = 0;
        String studentName = "";
        String studentSurname = "";
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

        Omat.addStudent(new Student(studentId, studentName, studentSurname, studentFaculty));

        onAnyUpdate();
    }

    @FXML
    protected void onEditSelectedStudent() {

        onAnyUpdate();
    }

    @FXML
    protected void onDeleteSelectedStudent() {

        onAnyUpdate();
    }


    @FXML
    protected void onDeleteAllStudents() {
        Omat.deleteAllStudents();

        onAnyUpdate();
    }
}
