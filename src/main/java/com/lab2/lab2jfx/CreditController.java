package com.lab2.lab2jfx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
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
    private TableView<SinglePayData> creditTableTableView;
    @FXML
    private TableColumn<SinglePayData, Integer> mounthTableCollum;
    @FXML
    private TableColumn<SinglePayData, Double> mounthPayTableCollum;
    @FXML
    private TableColumn<SinglePayData, Double> interestTableCollum;
    @FXML
    private TableColumn<SinglePayData, Double> creditTableCollum;
    @FXML
    private TableColumn<SinglePayData, Double> leftAmountTableCollum;

    @FXML
    private ChoiceBox<Credit> selectedCreditTableBox;
    @FXML
    private Spinner<Integer> tableStartMonthSpinner;
    @FXML
    private Spinner<Integer> tableEndMonthSpinner;

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

        mounthTableCollum.setCellValueFactory(cellData -> {
            int index = creditTableTableView.getItems().indexOf(cellData.getValue());
            System.out.println(index);
            return new SimpleIntegerProperty(index).asObject();
        });
        mounthPayTableCollum.setCellValueFactory(new PropertyValueFactory<>("monthPay"));
        interestTableCollum.setCellValueFactory(new PropertyValueFactory<>("interest"));
        creditTableCollum.setCellValueFactory(new PropertyValueFactory<>("credit"));
        leftAmountTableCollum.setCellValueFactory(new PropertyValueFactory<>("leftToPay"));
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
        creditTableTableView.getItems().clear();
    }

    @FXML
    protected void onFilterTable() {

    }

    @FXML
    protected void onShowCreditTableData() {
        var seletedCredit = selectedCreditTableBox.getValue();
        var simulatedData = seletedCredit.simulate();

        creditTableTableView.getItems().clear();
        creditTableTableView.getItems().addAll(simulatedData);
    }

    @FXML
    protected void onFilterGraph() {
        NumberAxis xAxis = (NumberAxis) creditGraphLineChart.getXAxis();

        int lowerBound = graphStartMonthSpinner.getValue();
        int upperBound = graphEndMonthSpinner.getValue();

        if(lowerBound + 1 < upperBound) {
            xAxis.setAutoRanging(false);

            xAxis.setLowerBound(lowerBound);
            xAxis.setUpperBound(upperBound);
        } else {
            // Todo lets user know that this is an invalid range
        }
    }

    @FXML
    protected void onRecalculateGraphs() {
        var seletedCredit = selectedCreditGraphChoiceBox.getValue();

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
        creditGraphLineChart.getData().clear();
    }

    @FXML
    protected void onClearSelectedCredit() {
        var creditList = CreditCalculator.creditList;

        Credit selectedItem = creditListView.getSelectionModel().getSelectedItem();
        creditList.remove(selectedItem);
        updateCreditUILists();
    }

    @FXML
    protected void onClearAllCredits() {
        var creditList = CreditCalculator.creditList;
        creditList.clear();
        updateCreditUILists();
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

        updateCreditUILists();
    }
}