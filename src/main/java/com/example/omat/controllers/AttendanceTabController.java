package com.example.omat.controllers;

import com.example.omat.Omat;
import com.example.omat.OmatApplication;
import com.example.omat.common.CommonController;
import com.example.omat.common.Month;
import com.example.omat.students.Faculty;
import com.example.omat.students.Group;
import com.example.omat.students.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AttendanceTabController extends CommonController {
    private ArrayList<Student> selectedStudents;

    @FXML private ChoiceBox<Group> groupChoiceBox;
    @FXML private ChoiceBox<Month> monthsChoiceBox;
    @FXML private ListView<Student> availableStudentListView;
    @FXML private ListView<Student> selectedStudentListView;
    @FXML private TableView allStudentTableView;
    @FXML private Text monthText;

    @Override
    public void onAnyUpdate() {
        var groupItems = FXCollections.observableArrayList(Omat.getGroups());
        groupChoiceBox.setItems(groupItems);

        monthText.setText(monthsChoiceBox.getValue().toString());

        Group selectedGroup = groupChoiceBox.getValue();

        availableStudentListView.getItems().clear();
        if(selectedGroup != null) {
            availableStudentListView.getItems().setAll(selectedGroup.getStudents());
        }
    }

    private void defaultInitialize() {
        monthsChoiceBox.getItems().setAll(
            Month.JANUARY,
            Month.FEBRUARY,
            Month.MARCH,
            Month.APRIL,
            Month.MAY,
            Month.JUNE,
            Month.JULY,
            Month.AUGUST,
            Month.SEPTEMBER,
            Month.OCTOBER,
            Month.NOVEMBER,
            Month.DECEMBER
        );
        monthsChoiceBox.getSelectionModel().select(0);

        selectedStudents = new ArrayList<>();

        groupChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number a, Number b) {
                OmatApplication.onAnyUpdate();
            }
        });

        monthsChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number a, Number b) {
                OmatApplication.onAnyUpdate();
            }
        });

        OmatApplication.onAnyUpdate();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        defaultInitialize();
    }

    @FXML private void onAddStudentToSelection() {

        OmatApplication.onAnyUpdate();
    }

    @FXML private void onDeleteStudent() {

        OmatApplication.onAnyUpdate();
    }

    @FXML private void onClearSelectedStudents() {

        OmatApplication.onAnyUpdate();
    }

    @FXML private void onSaveToFile() {

        OmatApplication.onAnyUpdate();
    }

    public void notifyError(String error) {
        // Todo
    }
}
