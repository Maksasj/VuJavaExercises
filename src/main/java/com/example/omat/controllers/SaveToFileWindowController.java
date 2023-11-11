package com.example.omat.controllers;

import com.example.omat.Omat;
import com.example.omat.OmatApplication;
import com.example.omat.common.CommonController;
import com.example.omat.common.FileFormat;
import com.example.omat.students.Faculty;
import com.example.omat.students.Group;
import com.example.omat.students.Student;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.UUID;

public class SaveToFileWindowController extends CommonController {
    private Stage selfStage;

    @FXML public TextField fileNameTextField;
    @FXML public TextField filePathTextField;
    @FXML public ChoiceBox<FileFormat> fileFormatChoiceBox;
    @FXML public Text fileWillBeSavedText;

    @Override
    public void onAnyUpdate() {

    }

    public void setStage(Stage stage) {
        selfStage = stage;
    }

    private void defaultInitialize() {
        Path currRelativePath = Paths.get("");
        filePathTextField.setText(currRelativePath.toAbsolutePath().toString());
        fileNameTextField.setText("data");
        fileFormatChoiceBox.getSelectionModel().select(0);

        updatePathToSave();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        fileFormatChoiceBox.getItems().setAll(
                FileFormat.CSV,
                FileFormat.PDF,
                FileFormat.EXCEL
        );

        fileNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            updatePathToSave();
        });

        filePathTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            updatePathToSave();
        });

        defaultInitialize();

        OmatApplication.onAnyUpdate();
    }

    public void notifyError(String error) {
        // Todo
    }

    private void updatePathToSave() {
        var formatVal = fileFormatChoiceBox.getValue();
        var s1 = fileNameTextField.getText();
        var s2 = filePathTextField.getText();

        if(formatVal != null) {
            fileWillBeSavedText.setText(s2 + "\\" + s1 + "." + fileFormatChoiceBox.getValue().getExtension());
        }
    }

    @FXML protected void onGenerateRandomName() {
        fileNameTextField.setText(UUID.randomUUID().toString());

        updatePathToSave();
    }

    @FXML protected void onFormatChange() {
        updatePathToSave();
    }

    @FXML protected void onChoicePath() {
        var directoryChooser = new DirectoryChooser();

        File selectedDirectory = directoryChooser.showDialog(selfStage);

        if(selectedDirectory == null) {
            notifyError("Save File: Failed to get selected directory");
            return;
        }

        filePathTextField.setText(selectedDirectory.getAbsolutePath());

        updatePathToSave();
    }

    @FXML protected void onExportToFile() {

    }

    @FXML protected void onResetToDefaults() {
        defaultInitialize();
    }
}
