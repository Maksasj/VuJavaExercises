package com.example.tableviewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableViewer extends Application {
    private static List<Human> humanRecords;
    private static List<CommonController> controllers;
    private static List<String> threadErrorMessages;

    synchronized public static void addThreadErrorMessage(String message) {
        threadErrorMessages.add(message);
    }

    synchronized public static void addHumanRecord(Human human) {
        humanRecords.add(human);
    }

    public static List<Human> getHumanRecords() {
        return new ArrayList<>(humanRecords);
    }

    public static List<String> getThreadErrorMessages() {
        return new ArrayList<>(threadErrorMessages);
    }

    public static void addController(CommonController controller) {
        controllers.add(controller);
    }

    private void startMainWindow(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TableViewer.class.getResource("mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("TableViewer!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void startThreadWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TableViewer.class.getResource("threadWindow.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 400, 600);

        var controller = (ThreadsController) fxmlLoader.getController();

        Stage stage = new Stage();
        stage.setTitle("TableViewer! Threads");
        stage.setScene(scene);
        stage.setResizable(false);

        controller.setState(stage);

        stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        humanRecords = new ArrayList<>();
        controllers = new ArrayList<>();
        threadErrorMessages = new ArrayList<>();

        startMainWindow(stage);
        startThreadWindow();
    }

    public static void main(String[] args) {
        launch();
    }

    synchronized public static void onAnyUpdate() {
        for(var controller : controllers) {
            controller.onAnyUpdate();
        }
    }
}