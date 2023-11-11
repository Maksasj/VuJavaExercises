package com.example.omat.controllers;

import com.example.omat.Omat;
import com.example.omat.OmatApplication;
import com.example.omat.common.CommonController;
import com.example.omat.common.FileFormat;
import com.example.omat.common.Month;
import com.example.omat.files.exporting.CSVFileExport;
import com.example.omat.files.exporting.CommonFileExport;
import com.example.omat.files.exporting.EXCELFileExport;
import com.example.omat.files.exporting.PDFFileExport;
import com.example.omat.files.importing.CSVFileImport;
import com.example.omat.files.importing.CommonFileImport;
import com.example.omat.files.importing.EXCELFileImport;
import com.example.omat.students.Student;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class LoadFromFileWindowController extends CommonController {
    private Stage selfStage;

    @FXML public TextField filePathTextField;
    @FXML public ChoiceBox<FileFormat> fileFormatChoiceBox;

    @Override
    public void onAnyUpdate() {

    }

    public void setStage(Stage stage) {
        selfStage = stage;
    }


    private void defaultInitialize() {
        Path currRelativePath = Paths.get("");

        filePathTextField.setText(currRelativePath.toAbsolutePath().toString() + "\\data.csv");
        fileFormatChoiceBox.getSelectionModel().select(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        fileFormatChoiceBox.getItems().setAll(
            FileFormat.CSV,
            FileFormat.EXCEL
        );

        defaultInitialize();

        OmatApplication.onAnyUpdate();
    }

    public void notifyError(String error) {
        // Todo
    }

    @FXML protected void onChoicePath() {
        var fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(selfStage);

        if(selectedFile == null) {
            notifyError("LOad File: Failed to get selected file");
            return;
        }

        filePathTextField.setText(selectedFile.getAbsolutePath());

        OmatApplication.onAnyUpdate();
    }

    @FXML protected void onLoadFromFile() {
        var format = fileFormatChoiceBox.getValue();
        var targetPath = filePathTextField.getText();

        CommonFileImport importer = null;

        switch (format) {
            case CSV -> {
                importer = new CSVFileImport(targetPath);
            }
            case EXCEL -> {
                importer = new EXCELFileImport(targetPath);
            }
        }

        importer.importt(Omat.getGroups(), Omat.getStudents());

        OmatApplication.onAnyUpdate();
    }

    @FXML
    protected void onResetToDefaults() {
        defaultInitialize();

        OmatApplication.onAnyUpdate();
    }
}
