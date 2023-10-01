package com.lab2.rkc;

import com.lab2.rkc.credit.Credit;
import com.lab2.rkc.scenes.CommonController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreditCalculator extends Application {
    public static List<Credit> creditList;
    private static List<CommonController> controllers;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CreditCalculator.class.getResource("mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Paskolos skaičiuoklė");
        stage.setScene(scene);
        stage.show();

        creditList = new ArrayList<>();
    }

    public static void main(String[] args) {
        controllers = new ArrayList<>();

        launch();
    }

    public static void addActiveController(CommonController controller) {
        controllers.add(controller);
    }

    public static void updateCreditUILists() {
        for(var controller : controllers) {
            controller.updateCreditUILists();
        }
    }
}