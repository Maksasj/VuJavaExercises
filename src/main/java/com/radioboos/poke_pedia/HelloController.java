package com.radioboos.poke_pedia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ListView<Pokemon> pokemonListView;

    @FXML
    protected void onHelloButtonClick() {
        ObservableList<Pokemon> items =FXCollections.observableArrayList (Pokedex.getInstance().getPokemons());
        pokemonListView.setItems(items);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        pokemonListView.setCellFactory(param -> new ListCell<Pokemon>() {
            private final ImageView imageView = new ImageView();

            @Override
            public void updateItem(Pokemon pokemon, boolean empty) {
                super.updateItem(pokemon, empty);

                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    var image = pokemon.getImage();

                    setText(pokemon.getEngName());

                    if(image != null) {
                        imageView.setImage(image);
                        setGraphic(imageView);
                    } else {
                        setGraphic(null);
                    }
                }
            }
        });
    }
}