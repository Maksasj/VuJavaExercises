/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.example.omat.controllers;

import com.example.omat.Omat;
import com.example.omat.OmatApplication;
import com.example.omat.common.AttendanceCell;
import com.example.omat.common.CommonController;
import com.example.omat.common.Month;
import com.example.omat.students.Faculty;
import com.example.omat.students.Group;
import com.example.omat.students.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentWindowController extends CommonController {
    private Student student;
    @FXML public GridPane monthDaysGridPane;
    @FXML public ChoiceBox<Month> selectedMonth;

    @FXML private Spinner<Integer> studentIDSpinner;
    @FXML private TextField studentSurnameTextField;
    @FXML private TextField studentNameTextField;
    @FXML private ChoiceBox<Faculty> studentFacultyChoiceBox;
    @FXML private ChoiceBox<Group> studentGroupChoiceBox;

    @FXML private Text studentIdText;
    @FXML private Text nameText;
    @FXML private Text surnameText;
    @FXML private Text facultyText;
    @FXML private Text groupText;

    @Override
    public void onAnyUpdate() {
        Month month = selectedMonth.getValue();

        monthDaysGridPane.getChildren().clear();

        if(month != null && student != null) {
            for(int i = 0; i < month.getDays(); ++i)
                monthDaysGridPane.add(new AttendanceCell(student, month, i + 1), i % 7, i / 7);

            var groupItems = FXCollections.observableArrayList(Omat.getGroups());
            studentGroupChoiceBox.setItems(groupItems);

            for(int g = 0; g < groupItems.size(); ++g) {
                for(var sstudent : groupItems.get(g).getStudents()) {
                    if(sstudent == student) {
                        studentGroupChoiceBox.getSelectionModel().select(g);
                    }
                }
            }

            studentIdText.setText(String.valueOf(student.getId()));
            nameText.setText(student.getName());
            surnameText.setText(student.getSurname());
            facultyText.setText(student.getFaculty().toString());
            groupText.setText(studentGroupChoiceBox.getValue().getName());
            studentSurnameTextField.setText(surnameText.getText());
            studentNameTextField.setText(nameText.getText());
        }
    }

    public void defaultInitialize(Student student) {
        selectedMonth.getItems().setAll(
            Month.JANUARY, Month.FEBRUARY, Month.MARCH,
            Month.APRIL, Month.MAY, Month.JUNE,
            Month.JULY, Month.AUGUST, Month.SEPTEMBER,
            Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER
        );
        selectedMonth.getSelectionModel().select(0);

        this.student = student;

        selectedMonth.getSelectionModel()
            .selectedItemProperty()
            .addListener( (ObservableValue<? extends Month> observable, Month oldValue, Month newValue) -> OmatApplication.updateControllers() );

        setIntegerValueFactory(studentIDSpinner, 0, Integer.MAX_VALUE);

        studentFacultyChoiceBox.getItems().setAll(
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

        OmatApplication.updateControllers();
    }

    public Group getStudentGroup(Student student) {
        var groupItems = FXCollections.observableArrayList(Omat.getGroups());

        for(var group : groupItems) {
            for(var sstudent : group.getStudents()) {
                if(sstudent == student) {
                    return group;
                }
            }
        }

        return null;
    }

    public void initialize(Student student) {
        defaultInitialize(student);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        defaultInitialize(null);
    }

    @FXML
    private void onResetChanges() {
        studentIdText.setText(String.valueOf(student.getId()));
        nameText.setText(student.getName());
        surnameText.setText(student.getSurname());
        facultyText.setText(student.getFaculty().toString());
        groupText.setText(studentGroupChoiceBox.getValue().getName());

        studentSurnameTextField.setText(nameText.getText());
        studentNameTextField.setText(surnameText.getText());

        Group group = studentGroupChoiceBox.getValue();
        studentGroupChoiceBox.getSelectionModel().select(getStudentGroup(student));
        studentFacultyChoiceBox.getSelectionModel().select(student.getFaculty());

        OmatApplication.updateControllers();
    }

    private void deleteFromGroups(Student student) {
        var groupItems = FXCollections.observableArrayList(Omat.getGroups());

        for(var group : groupItems) {
            group.getStudents().remove(student);
        }
    }

    @FXML
    private void onSaveChanges() {
        student.setId(studentIDSpinner.getValue());
        student.setName(studentNameTextField.getText());
        student.setSurname(studentSurnameTextField.getText());
        student.setFaculty(studentFacultyChoiceBox.getValue());

        deleteFromGroups(student);
        Group group = studentGroupChoiceBox.getValue();
        group.addStudent(student);

        OmatApplication.updateControllers();
    }

    public void notifyError(String error) {
        // Todo
    }
}
