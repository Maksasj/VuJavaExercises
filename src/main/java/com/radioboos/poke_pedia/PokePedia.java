package com.radioboos.poke_pedia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

public class PokePedia extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PokePedia.class.getResource("pokePediaMain.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("PokePedia");
        stage.setScene(scene);
        stage.show();

        Pokedex.getInstance().loadPokedexFromFile("C:\\Programming\\java\\poke_pedia\\src\\main\\resources\\com\\radioboos\\poke_pedia\\database.csv");
    }

    public static void main(String[] args) {
        launch();
    }
}