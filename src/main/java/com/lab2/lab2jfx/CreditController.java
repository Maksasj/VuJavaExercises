package com.lab2.lab2jfx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CreditController implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        /*
        creditAmountTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String valueToBeSet = newValue;

                valueToBeSet = valueToBeSet.replaceAll("[^.,\\d]", "");
                valueToBeSet = valueToBeSet.replaceAll("\\.(?=.*\\.)", "");
                valueToBeSet = valueToBeSet.replaceAll(",(?=.*,)", "");

                creditAmountTextField.setText(valueToBeSet);
            }
        });
        */
    }

    private void updateCreditListView() {
        var creditList = CreditCalculator.creditList;

        creditListView.getItems().clear();
        creditListView.getItems().addAll(creditList);
    }

    @FXML
    protected void onCreditAdd() {
        var creditList = CreditCalculator.creditList;

        String creditName = null;
        Double creditAmount = null;
        Double creditRate = null;

        Integer year = null;
        Integer month = null;

        ReplaymentScheduleType scheduleType = null;

        boolean valid = false;
        try {
            creditName = new String(creditNameTextField.getText());
            creditAmount = Double.parseDouble(creditAmountTextField.getText());
            creditRate = Double.parseDouble(creditRateTextField.getText());

            year = periodYearSpinner.getValue();
            month = periodMonthSpinner.getValue();

            scheduleType = scheduleTypeChoiceBox.getValue();

            valid = true;
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }

        if(!valid)
            return;

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

        updateCreditListView();
    }
}