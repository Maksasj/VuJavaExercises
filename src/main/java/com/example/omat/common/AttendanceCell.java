package com.example.omat.common;

import com.example.omat.OmatApplication;
import com.example.omat.students.Student;
import com.example.omat.students.attendance.YearAttendanceData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class AttendanceCell extends AnchorPane {
    private Student student;
    private Month month;
    private int day;

    @FXML public AnchorPane attendanceCellAnchorPane;
    @FXML public Text dayText;
    @FXML public Text statusText;

    public AttendanceCell(Student student, Month month, int day) {
        FXMLLoader loader = new FXMLLoader(OmatApplication.class.getResource("studentAttendanceCell.fxml"));
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dayText.setText("Day " + day);
        statusText.setText(" ");

        getChildren().addAll(attendanceCellAnchorPane);

        this.student = student;
        this.month = month;
        this.day = day - 1;

        display();
    }

    private void display() {
        if(student == null)
            return;

        YearAttendanceData yearAttendanceData = student.getAttendanceData();
        boolean value = yearAttendanceData.months.get(month).getDay(day);

        if(value)
           statusText.setText("+");
        else
            statusText.setText(" ");
    }

    @FXML
    protected void onClick() {
        YearAttendanceData yearAttendanceData = student.getAttendanceData();

        boolean value = yearAttendanceData.months.get(month).getDay(day);
        yearAttendanceData.months.get(month).markDay(day, !value);

        display();

        OmatApplication.onAnyUpdate();
    }

    @FXML
    protected void onEnter() {
        attendanceCellAnchorPane.setBackground(Background.fill(Color.LIGHTGRAY));
    }

    @FXML
    protected void onExit() {
        attendanceCellAnchorPane.setBackground(Background.fill(new Color(0.956862745, 0.956862745, 0.956862745, 1.0)));
    }
}