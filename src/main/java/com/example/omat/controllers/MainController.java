package com.example.omat.controllers;

import com.example.omat.OmatApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onLoadFromFile() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OmatApplication.class.getResource("fileImportWindow.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();

        LoadFromFileWindowController controller = fxmlLoader.getController();
        controller.setStage(stage);

        Scene scene = new Scene(root);

        stage.setTitle("OMAT Load from file ");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        OmatApplication.onAnyUpdate();
    }
}