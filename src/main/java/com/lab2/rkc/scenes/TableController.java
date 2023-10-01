package com.lab2.rkc.scenes;

import com.lab2.rkc.CreditCalculator;
import com.lab2.rkc.PayData;
import com.lab2.rkc.PayDataTable;
import com.lab2.rkc.credit.Credit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TableController implements Initializable {
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

    @FXML private Text errorTextTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearErrorLog();

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
        errorTextTable.setText("");
    }

    private void notifyError(String message) {
        errorTextTable.setText(message);
    }

    private void updateCreditUILists() {
        var creditList = CreditCalculator.creditList;

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
                notifyError("Table: No credit selected");
                return;
            }

            var simulatedData = seletedCredit.simulate();
            fillTableWithData(lowerBound, upperBound, simulatedData);
        } else {
            notifyError("Error: Provided range is invalid");
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
            notifyError("Table: No credit selected");
            return;
        }

        var simulatedData = seletedCredit.simulate();
        fillTableWithData(0, simulatedData.size(), simulatedData);
    }
}