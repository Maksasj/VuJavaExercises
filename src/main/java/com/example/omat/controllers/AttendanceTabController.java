package com.example.omat.controllers;

import com.example.omat.Omat;
import com.example.omat.OmatApplication;
import com.example.omat.common.CommonController;
import com.example.omat.common.Month;
import com.example.omat.students.Group;
import com.example.omat.students.Student;
import javafx.beans.property.SimpleStringProperty;
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
    private ArrayList<Student> selectedStudents = new ArrayList<>();

    @FXML private ChoiceBox<Group> groupChoiceBox;
    @FXML private ChoiceBox<Month> monthsChoiceBox;
    @FXML private ListView<Student> availableStudentListView;
    @FXML private ListView<Student> selectedStudentListView;
    @FXML private TableView<Student> allStudentTableView;
    @FXML private Text monthText;

    @Override
    public void onAnyUpdate() {
        var groupItems = FXCollections.observableArrayList(Omat.getGroups());
        groupChoiceBox.setItems(groupItems);

        if(!groupChoiceBox.getItems().isEmpty())
            groupChoiceBox.getSelectionModel().select(0);

        selectedStudentListView.getItems().setAll(selectedStudents);

        updateSelectedGroup();

        fillTable();
    }

    private void updateSelectedGroup() {
        Group selectedGroup = groupChoiceBox.getValue();

        if(selectedGroup != null) {
            availableStudentListView.getItems().clear();
            availableStudentListView.getItems().setAll(selectedGroup.getStudents());

            availableStudentListView.getItems().removeAll(selectedStudents);
        }
    }

    @FXML
    protected void onGroupChange() {
        updateSelectedGroup();
    }

    private void fillTable() {
        allStudentTableView.getItems().clear();
        allStudentTableView.getColumns().clear();

        var nameCol = new TableColumn<Student, String>("Name");
        nameCol.setCellValueFactory((var student) -> new SimpleStringProperty(student.getValue().getName()));

        allStudentTableView.getColumns().add(nameCol);

        var selectedMonth = monthsChoiceBox.getValue();
        for(int i = 0; i < selectedMonth.getDays(); ++i) {
            var dayCol = new TableColumn<Student, String>("Day " + (i + 1));

            int finalI = i;
            dayCol.setCellValueFactory((var data) -> {
                    if(data.getValue().getAttendanceData().months.get(selectedMonth).getDay(finalI)) {
                        return new SimpleStringProperty("+");
                    }

                    return new SimpleStringProperty(" ");
            });

            allStudentTableView.getColumns().add(dayCol);
        }

        allStudentTableView.getItems().clear();
        allStudentTableView.getItems().addAll(selectedStudents);
    }

    @FXML
    protected void onMonthChange() {
        monthText.setText(monthsChoiceBox.getValue().toString());

        OmatApplication.onAnyUpdate();
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

        fillTable();

        OmatApplication.onAnyUpdate();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        defaultInitialize();
    }

    @FXML private void onAddStudentToSelection() {
        var selected = availableStudentListView.getSelectionModel().getSelectedItem();

        if(selected != null)
            if(!selectedStudents.contains(selected))
                selectedStudents.add(selected);

        OmatApplication.onAnyUpdate();
    }

    @FXML private void onDeleteStudent() {
        var selected = selectedStudentListView.getSelectionModel().getSelectedItem();

        if(selected != null) {
            selectedStudents.remove(selected);
        }

        OmatApplication.onAnyUpdate();
    }

    @FXML private void onClearSelectedStudents() {
        selectedStudents.clear();

        OmatApplication.onAnyUpdate();
    }

    @FXML private void onSaveToFile() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OmatApplication.class.getResource("fileExportWindow.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();

        SaveToFileWindowController controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setSaveData(selectedStudents, monthsChoiceBox.getValue());

        Scene scene = new Scene(root);

        stage.setTitle("OMAT Save to file ");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        OmatApplication.onAnyUpdate();
    }

    public void notifyError(String error) {
        // Todo
    }
}
