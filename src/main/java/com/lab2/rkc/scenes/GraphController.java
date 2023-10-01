package com.lab2.rkc.scenes;

import com.lab2.rkc.CreditCalculator;
import com.lab2.rkc.credit.Credit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class GraphController implements Initializable {
    @FXML
    private LineChart<Number, Number> creditGraphLineChart;
    @FXML
    private ChoiceBox<Credit> selectedCreditGraphChoiceBox;
    @FXML
    private Spinner<Integer> graphStartMonthSpinner;
    @FXML
    private Spinner<Integer> graphEndMonthSpinner;

    @FXML private Text errorTextGraphs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearErrorLog();

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
    }

    private void clearErrorLog() {
        errorTextGraphs.setText("");
    }

    private void notifyError(String message) {
        errorTextGraphs.setText(message);
    }

    private void updateCreditUILists() {
        var creditList = CreditCalculator.creditList;

        selectedCreditGraphChoiceBox.getItems().clear();
        selectedCreditGraphChoiceBox.getItems().addAll(creditList);
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
            notifyError("Error: Provided range is invalid");
        }
    }

    @FXML
    protected void onRecalculateGraphs() {
        clearErrorLog();

        var seletedCredit = selectedCreditGraphChoiceBox.getValue();

        if(seletedCredit == null) {
            notifyError("Table: No credit selected");
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
}