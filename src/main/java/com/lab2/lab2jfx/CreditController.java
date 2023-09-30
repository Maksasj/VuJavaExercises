package com.lab2.lab2jfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CreditController implements Initializable {
    @FXML
    private Spinner<Integer> periodYearSpinner;
    @FXML
    private Spinner<Integer> periodMonthSpinner;

    @FXML
    private ChoiceBox<ReplaymentScheduleType> scheduleTypeChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scheduleTypeChoiceBox.getItems().addAll(
                ReplaymentScheduleType.ANNUITY,
                ReplaymentScheduleType.LINEAR
        );

        // SpinnerValueFactory<Integer>
    }

    @FXML
    protected void onKreditAdd() {

    }
}