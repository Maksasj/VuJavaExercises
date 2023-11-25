package com.moody_blues.client;

import com.moody_blues.client.controllers.CommonController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MoodyBluesClient extends Application {
    private static ClientData clientData = null;
    private static boolean runnning = true;
    private static ArrayList<CommonController> mainControllers = new ArrayList<>();
    private static ArrayList<CommonController> chatControllers = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MoodyBluesClient.class.getResource("connetionWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Moody Blues");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void addControllerAsMain(CommonController controller) {
        mainControllers.add(controller);
    }
    public static void addControllerAsChat(CommonController controller) {
        chatControllers.add(controller);
    }

    public static boolean isRunnning() {
        return runnning;
    }

    public static void main(String[] args) {
        launch();
    }

    public static ClientData getClientData() {
        return clientData;
    }

    synchronized public static void onAnyUpdate() {
        for(var controller : mainControllers)
            controller.onAnyUpdate();

        for(var controller : chatControllers)
            controller.onAnyUpdate();
    }

    synchronized public static void setClientData(ClientData clientData) {
        MoodyBluesClient.clientData = clientData;
    }
}