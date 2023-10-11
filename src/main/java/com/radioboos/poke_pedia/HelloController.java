package com.radioboos.poke_pedia;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private ListView<Pokemon> pokemonListView;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Pokemon> items =FXCollections.observableArrayList (new Pokemon());
        pokemonListView.setItems(items);

        System.out.println("Poggers !");

        pokemonListView.setCellFactory(param -> new ListCell<Pokemon>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(Pokemon pokemon, boolean empty) {
                super.updateItem(pokemon, empty);

                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(pokemon.image);

                    setText("Pokemon");
                    setGraphic(imageView);
                }
            }
        });
    }
}