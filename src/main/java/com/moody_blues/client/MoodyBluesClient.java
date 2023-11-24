package com.moody_blues.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MoodyBluesClient extends Application {
    private static ClientData clientData = null;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MoodyBluesClient.class.getResource("connetionWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Moody Blues");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static ClientData getClientData() {
        return clientData;
    }

    synchronized public static void setClientData(ClientData clientData) {
        MoodyBluesClient.clientData = clientData;
    }
}