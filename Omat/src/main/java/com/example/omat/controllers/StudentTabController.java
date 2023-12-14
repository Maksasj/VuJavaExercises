/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.example.omat.controllers;

import com.example.omat.Omat;
import com.example.omat.OmatApplication;
import com.example.omat.common.CommonController;
import com.example.omat.common.Month;
import com.example.omat.students.Faculty;
import com.example.omat.students.Group;
import com.example.omat.students.Student;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
        if(!groupItems.isEmpty())
            studentGroupChoiceBox.getSelectionModel().select(0);

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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        OmatApplication.updateControllers();
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

        OmatApplication.updateControllers();
    }

    @FXML
    protected void onEditSelectedStudent() throws IOException {
        var selected = studentsListView.getSelectionModel().getSelectedItem();
        if(selected == null) {
            notifyError("Student is not selected");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(OmatApplication.class.getResource("studentWindow.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();

        StudentWindowController controller = fxmlLoader.getController();
        controller.initialize(selected);

        Scene scene = new Scene(root);

        scene.setUserData(selected);

        stage.setTitle("OMAT " + selected.getName());
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        OmatApplication.updateControllers();
    }

    @FXML
    protected void onDeleteSelectedStudent() {
        var selected = studentsListView.getSelectionModel().getSelectedItem();
        if(selected != null)
            Omat.getStudents().remove(selected);

        OmatApplication.updateControllers();
    }


    @FXML
    protected void onDeleteAllStudents() {
        Omat.deleteAllStudents();

        OmatApplication.updateControllers();
    }
}
