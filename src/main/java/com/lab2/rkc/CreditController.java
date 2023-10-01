package com.lab2.rkc;

import com.lab2.rkc.credit.AnnuityCredit;
import com.lab2.rkc.credit.Credit;
import com.lab2.rkc.credit.LinearCredit;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    @FXML
    private LineChart<Number, Number> creditGraphLineChart;
    @FXML
    private ChoiceBox<Credit> selectedCreditGraphChoiceBox;
    @FXML
    private Spinner<Integer> graphStartMonthSpinner;
    @FXML
    private Spinner<Integer> graphEndMonthSpinner;

    @FXML
    private TableView<PayDataTable> creditTableTableView;
    @FXML
    private TableColumn<PayDataTable, Integer> mounthTableCollum;
    @FXML
    private TableColumn<PayDataTable, Double> mounthPayTableCollum;
    @FXML
    private TableColumn<PayDataTable, Double> interestTableCollum;
    @FXML
    private TableColumn<PayDataTable, Double> creditTableCollum;
    @FXML
    private TableColumn<PayDataTable, Double> leftAmountTableCollum;

    @FXML
    private ChoiceBox<Credit> selectedCreditTableBox;
    @FXML
    private Spinner<Integer> tableStartMonthSpinner;
    @FXML
    private Spinner<Integer> tableEndMonthSpinner;

    @FXML private Text errorTextInput;
    @FXML private Text errorTextGraphs;
    @FXML private Text errorTextTable;

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

        {
            var valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE);
            valueFactory.setValue(0);
            graphStartMonthSpinner.setValueFactory(valueFactory);
        }

        {
            var valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE);
            valueFactory.setValue(0);
            graphEndMonthSpinner.setValueFactory(valueFactory);
        }

        {
            var valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE);
            valueFactory.setValue(0);
            tableStartMonthSpinner.setValueFactory(valueFactory);
        }

        {
            var valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE);
            valueFactory.setValue(0);
            tableEndMonthSpinner.setValueFactory(valueFactory);
        }

        mounthTableCollum.setCellValueFactory(new PropertyValueFactory<>("month"));
        mounthPayTableCollum.setCellValueFactory(new PropertyValueFactory<>("monthPay"));
        interestTableCollum.setCellValueFactory(new PropertyValueFactory<>("interest"));
        creditTableCollum.setCellValueFactory(new PropertyValueFactory<>("credit"));
        leftAmountTableCollum.setCellValueFactory(new PropertyValueFactory<>("leftToPay"));
    }

    private void clearErrorLog() {
        errorTextInput.setText("");
        errorTextGraphs.setText("");
        errorTextTable.setText("");
    }

    private void notifyError(SubScene targetScene, String message) {
        switch (targetScene) {
            case INPUT -> errorTextInput.setText(message);
            case GRAPHS -> errorTextGraphs.setText(message);
            case TABLE -> errorTextTable.setText(message);
        }
    }

    private void updateCreditUILists() {
        var creditList = CreditCalculator.creditList;

        creditListView.getItems().clear();
        creditListView.getItems().addAll(creditList);

        selectedCreditGraphChoiceBox.getItems().clear();
        selectedCreditGraphChoiceBox.getItems().addAll(creditList);

        selectedCreditTableBox.getItems().clear();
        selectedCreditTableBox.getItems().addAll(creditList);
    }

    @FXML
    protected void onClearTable() {
        clearErrorLog();

        creditTableTableView.getItems().clear();
    }

    @FXML
    protected void onFilterTable() {
        clearErrorLog();

        int lowerBound = tableStartMonthSpinner.getValue();
        int upperBound = tableEndMonthSpinner.getValue();

        var seletedCredit = selectedCreditTableBox.getValue();

        if(lowerBound + 1 < upperBound) {
            if(seletedCredit == null) {
                notifyError(SubScene.TABLE, "Table: No credit selected");
                return;
            }

            var simulatedData = seletedCredit.simulate();
            fillTableWithData(lowerBound, upperBound, simulatedData);
        } else {
            notifyError(SubScene.TABLE, "Error: Provided range is invalid");
        }
    }

    private void fillTableWithData(int startRange, int endRange, List<PayData> simulatedData) {
        List<PayDataTable> tableData = new ArrayList<>();

        for(int i = startRange; i < endRange; ++i)
            tableData.add(new PayDataTable(i, simulatedData.get(i)));

        creditTableTableView.getItems().clear();
        creditTableTableView.getItems().addAll(tableData);
    }

    @FXML
    protected void onShowCreditTableData() {
        clearErrorLog();

        var seletedCredit = selectedCreditTableBox.getValue();

        if(seletedCredit == null) {
            notifyError(SubScene.TABLE, "Table: No credit selected");
            return;
        }

        var simulatedData = seletedCredit.simulate();
        fillTableWithData(0, simulatedData.size(), simulatedData);
    }

    @FXML
    protected void onFilterGraph() {
        clearErrorLog();

        NumberAxis xAxis = (NumberAxis) creditGraphLineChart.getXAxis();

        int lowerBound = graphStartMonthSpinner.getValue();
        int upperBound = graphEndMonthSpinner.getValue();

        if(lowerBound + 1 < upperBound) {
            xAxis.setAutoRanging(false);

            xAxis.setLowerBound(lowerBound);
            xAxis.setUpperBound(upperBound);
        } else {
            notifyError(SubScene.GRAPHS, "Error: Provided range is invalid");
        }
    }

    @FXML
    protected void onRecalculateGraphs() {
        clearErrorLog();

        var seletedCredit = selectedCreditGraphChoiceBox.getValue();

        if(seletedCredit == null) {
            notifyError(SubScene.GRAPHS, "Table: No credit selected");
            return;
        }

        var simulatedData = seletedCredit.simulate();
        var series = new XYChart.Series<Number, Number>();

        for(int m = 0; m < simulatedData.size(); ++m) {
            series.getData().add(
                new XYChart.Data<>(
                        m,
                        simulatedData.get(m).getMonthPay()
                ));
        }

        series.setName(seletedCredit.getCreditName());

        creditGraphLineChart.getData().add(series);
    }

    @FXML
    protected void onClearGraphs() {
        clearErrorLog();

        creditGraphLineChart.getData().clear();
    }

    @FXML
    protected void onClearSelectedCredit() {
        clearErrorLog();

        var creditList = CreditCalculator.creditList;
        var selectedItem = creditListView.getSelectionModel().getSelectedItem();

        if(selectedItem == null) {
            notifyError(SubScene.INPUT, "Error: No credit selected");
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
            notifyError(SubScene.INPUT, "Error: Failed to parse credit name");
            return;
        }

        if(creditName.contentEquals("")) {
            notifyError(SubScene.INPUT, "Error: Empty name is now allowed");
            return;
        }

        try {
            creditAmount = Double.parseDouble(creditAmountTextField.getText());
        } catch (Exception exception) {
            notifyError(SubScene.INPUT, "Error: Failed to parse credit amount, expected numeric value");
            return;
        }

        try {
            creditRate = Double.parseDouble(creditRateTextField.getText());
        } catch (Exception exception) {
            notifyError(SubScene.INPUT, "Error: Failed to parse credit rate, expected numeric value");
            return;
        }

        try {
            year = periodYearSpinner.getValue();
        } catch (Exception exception) {
            notifyError(SubScene.INPUT, "Error: Failed to get period year");
            return;
        }

        try {
            month = periodMonthSpinner.getValue();
        } catch (Exception exception) {
            notifyError(SubScene.INPUT, "Error: Failed to get period month");
            return;
        }

        int time = year * 12 + month;
        if(time == 0) {
            notifyError(SubScene.INPUT, "Error: time period is equal to 0");
            return;
        }

        try {
            scheduleType = scheduleTypeChoiceBox.getValue();
        } catch (Exception exception) {
            notifyError(SubScene.INPUT, "Error: Failed to get period schedule type");
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