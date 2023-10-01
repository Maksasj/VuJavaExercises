package com.lab2.rkc.scenes;

import com.lab2.rkc.CreditCalculator;
import com.lab2.rkc.ReplaymentScheduleType;
import com.lab2.rkc.credit.AnnuityCredit;
import com.lab2.rkc.credit.Credit;
import com.lab2.rkc.credit.LinearCredit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class InputController implements Initializable {
    @FXML
    private TextField creditNameTextField;
    @FXML
    private TextField creditAmountTextField;
    @FXML
    private TextField creditRateTextField;
    @FXML
    private Spinner<Integer> periodYearSpinner;
    @FXML
    private Spinner<Integer> periodMonthSpinner;
    @FXML
    private ChoiceBox<ReplaymentScheduleType> scheduleTypeChoiceBox;
    @FXML
    private ListView<Credit> creditListView;
    @FXML
    private Text errorTextInput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearErrorLog();

        scheduleTypeChoiceBox.getItems().addAll(
                ReplaymentScheduleType.ANNUITY,
                ReplaymentScheduleType.LINEAR
        );

        {
            var valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE);
            valueFactory.setValue(0);
            periodYearSpinner.setValueFactory(valueFactory);
        }

        {
            var valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 12);
            valueFactory.setValue(0);
            periodMonthSpinner.setValueFactory(valueFactory);
        }
    }

    private void clearErrorLog() {
        errorTextInput.setText("");
    }

    private void notifyError(String message) {
        errorTextInput.setText(message);
    }

    private void updateCreditUILists() {
        var creditList = CreditCalculator.creditList;

        creditListView.getItems().clear();
        creditListView.getItems().addAll(creditList);
    }

    @FXML
    protected void onClearSelectedCredit() {
        clearErrorLog();

        var creditList = CreditCalculator.creditList;
        var selectedItem = creditListView.getSelectionModel().getSelectedItem();

        if(selectedItem == null) {
            notifyError("Error: No credit selected");
            return;
        }

        creditList.remove(selectedItem);
        updateCreditUILists();
    }

    @FXML
    protected void onClearAllCredits() {
        clearErrorLog();

        var creditList = CreditCalculator.creditList;
        creditList.clear();
        updateCreditUILists();
    }

    @FXML
    protected void onCreditAdd() {
        clearErrorLog();

        String creditName;
        Double creditAmount, creditRate;
        Integer year, month;
        ReplaymentScheduleType scheduleType;

        try {
            creditName = new String(creditNameTextField.getText());
        } catch (Exception exception) {
            notifyError("Error: Failed to parse credit name");
            return;
        }

        if(creditName.contentEquals("")) {
            notifyError("Error: Empty name is now allowed");
            return;
        }

        try {
            creditAmount = Double.parseDouble(creditAmountTextField.getText());
        } catch (Exception exception) {
            notifyError("Error: Failed to parse credit amount, expected numeric value");
            return;
        }

        try {
            creditRate = Double.parseDouble(creditRateTextField.getText());
        } catch (Exception exception) {
            notifyError("Error: Failed to parse credit rate, expected numeric value");
            return;
        }

        try {
            year = periodYearSpinner.getValue();
        } catch (Exception exception) {
            notifyError("Error: Failed to get period year");
            return;
        }

        try {
            month = periodMonthSpinner.getValue();
        } catch (Exception exception) {
            notifyError("Error: Failed to get period month");
            return;
        }

        int time = year * 12 + month;
        if(time == 0) {
            notifyError("Error: time period is equal to 0");
            return;
        }

        try {
            scheduleType = scheduleTypeChoiceBox.getValue();
        } catch (Exception exception) {
            notifyError("Error: Failed to get period schedule type");
            return;
        }

        var creditList = CreditCalculator.creditList;

        switch (scheduleType) {
            case ANNUITY -> creditList.add(
                    new AnnuityCredit(
                            creditName,
                            creditAmount,
                            creditRate,
                            year * 12 + month
                    )
            );
            case LINEAR -> creditList.add(
                    new LinearCredit(
                            creditName,
                            creditAmount,
                            creditRate,
                            year * 12 + month
                    )
            );
        }

        updateCreditUILists();
    }
}
