/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/
package com.lab2.rkc.scenes;

import com.lab2.rkc.CreditCalculator;
import com.lab2.rkc.credit.Credit;
import com.lab2.rkc.credit.Deferral;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.UUID;

public class FileSaveController extends CommonController implements Initializable {

    @FXML
    private ChoiceBox<Credit> selectedCreditChoiceBox;
    @FXML
    private ListView<Deferral> selectedCreditDeferralListView;

    @FXML private CheckBox collumTimeCheckBox;
    @FXML private CheckBox collumMounthPayCheckBox;
    @FXML private CheckBox collumInterestCheckBox;
    @FXML private CheckBox collumCreditCheckBox;
    @FXML private CheckBox collumLeftAmountCheckBox;

    @FXML
    private CheckBox enableTimePeriodOptionCheckBox;
    @FXML
    private Spinner<Integer> startMonthPeriod;
    @FXML
    private Spinner<Integer> startEndPeriod;

    @FXML
    private TextField fileNameTextField;
    @FXML
    private TextField folderNameTextField;
    @FXML
    private Text pathPreviewText;

    @FXML
    private CheckBox excelTableCheckBox;

    @FXML
    private Text errorText;

    private String pathToSave;

    @Override
    public void clearErrorLog() {
        errorText.setText("");
    }

    @Override
    public void notifyError(String message) {
        errorText.setText(message);
    }

    @Override
    public void updateCreditUILists() {
        var creditList = CreditCalculator.creditList;

        selectedCreditChoiceBox.getItems().clear();
        selectedCreditChoiceBox.getItems().addAll(creditList);

        selectedCreditDeferralListView.getItems().clear();
        var selectedCredit = selectedCreditChoiceBox.getValue();

        if(selectedCredit != null) {
            selectedCreditDeferralListView.getItems().addAll(selectedCredit.getDeferrals());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        Path currRelativePath = Paths.get("");
        folderNameTextField.setText(currRelativePath.toAbsolutePath().toString());
        fileNameTextField.setText("table.csv");

        {
            var valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE);
            valueFactory.setValue(0);
            startMonthPeriod.setValueFactory(valueFactory);
        }

        {
            var valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE);
            valueFactory.setValue(0);
            startEndPeriod.setValueFactory(valueFactory);
        }

        updatePathToSave();
    }

    @FXML
    protected void onGenerateRandomFileName() {
        clearErrorLog();

        fileNameTextField.setText(UUID.randomUUID().toString() + ".csv");

        updatePathToSave();
    }

    private void updatePathToSave() {
        clearErrorLog();

        pathToSave = folderNameTextField.getText() + "\\" + fileNameTextField.getText();

        pathPreviewText.setText("Failas bus išsaugotas: " + pathToSave);
    }

    @FXML
    protected void onChoiceTargetPath() {
        clearErrorLog();

        var directoryChooser = new DirectoryChooser();

        File selectedDirectory = directoryChooser.showDialog(CreditCalculator.primaryStage);

        if(selectedDirectory == null) {
            notifyError("Save File: Failed to get selected directory");
            return;
        }


        folderNameTextField.setText(selectedDirectory.getAbsolutePath());

        updatePathToSave();
    }

    @FXML
    protected void onShowDeferrals() {
        clearErrorLog();

        var selectedCredit = selectedCreditChoiceBox.getValue();

        if(selectedCredit != null) {
            selectedCreditDeferralListView.getItems().addAll(selectedCredit.getDeferrals());
        }
    }

    @FXML
    protected void onEnableTimePeriod() {
        clearErrorLog();

        boolean isSelected = enableTimePeriodOptionCheckBox.isSelected();

        if(isSelected) {
            startMonthPeriod.setDisable(false);
            startEndPeriod.setDisable(false);
        } else {
            startMonthPeriod.setDisable(true);
            startEndPeriod.setDisable(true);
        }
    }

    @FXML
    protected void onSaveToFile() {
        clearErrorLog();

        var seletedCredit = selectedCreditChoiceBox.getValue();

        if(seletedCredit == null) {
            notifyError("Save File: No credit selected");
            return;
        }

        int lowerBoundPeriod = 0;
        int upperBoundPeriod = 0;

        if(!startMonthPeriod.isDisabled()) {
            lowerBoundPeriod = startMonthPeriod.getValue();
            upperBoundPeriod = startEndPeriod.getValue();

            if(!(lowerBoundPeriod + 1 < upperBoundPeriod)) {
                notifyError("Error: Provided range is invalid");
                return;
            }
        }

        try {
            File file = new File(pathToSave);
            if (file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(pathToSave);
                var simulatedData = seletedCredit.simulate();

                String delim = ",";
                if(excelTableCheckBox.isSelected())
                    delim = ";";

                // Write headers
                if(collumTimeCheckBox.isSelected()) fileWriter.write("Laikas" + delim);
                if(collumMounthPayCheckBox.isSelected()) fileWriter.write("Mėnesinė įmoka" + delim);
                if(collumInterestCheckBox.isSelected()) fileWriter.write("Palūkanos" + delim);
                if(collumCreditCheckBox.isSelected()) fileWriter.write("Kreditas" + delim);
                if(collumLeftAmountCheckBox.isSelected()) fileWriter.write("Liko sumokėti" + delim);
                fileWriter.write("\n");

                int iStart = 0;
                int iEnd = simulatedData.size();

                if(!startMonthPeriod.isDisabled()) {
                    iStart = lowerBoundPeriod;
                    iEnd = Math.min(upperBoundPeriod, iEnd);
                }

                for(int i = iStart; i < iEnd; ++i) {
                    var data = simulatedData.get(i);

                    if(collumTimeCheckBox.isSelected()) fileWriter.write(i + delim);
                    if(collumMounthPayCheckBox.isSelected()) fileWriter.write(data.getMonthPay() + delim);
                    if(collumInterestCheckBox.isSelected()) fileWriter.write(data.getInterest() + delim);
                    if(collumCreditCheckBox.isSelected()) fileWriter.write(data.getCredit() + delim);
                    if(collumLeftAmountCheckBox.isSelected()) fileWriter.write(data.getLeftToPay() + delim);

                    fileWriter.write("\n");
                }

                fileWriter.close();
            } else {
                notifyError("Save File: File already exists.");
            }
        } catch (IOException e) {
            notifyError("Save File: Failed to create file");
        }
    }
}
