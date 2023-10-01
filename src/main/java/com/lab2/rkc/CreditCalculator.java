package com.lab2.rkc;

import com.lab2.rkc.credit.Credit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreditCalculator extends Application {
    public static List<Credit> creditList;

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
        launch();
    }
}