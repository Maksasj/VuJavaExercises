package com.radioboos.poke_pedia.controllers;

import com.radioboos.poke_pedia.PokePedia;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    // FXML
    // rivate ListView<Pokemon> pokemonListView;

    @FXML
    private Button findDesiredPokemonButton;

    @FXML
    protected void onHelloButtonClick() {
        // ObservableList<Pokemon> items = FXCollections.observableArrayList (Pokedex.getInstance().getPokemons());
        // pokemonListView.setItems(items);
    }

    @FXML
    protected void onFindDesiredPokemon() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PokePedia.class.getResource("pokePediaResult.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();

        // OutputController outputController = fxmlLoader.getController();

        //1. UserData
        //stage.setUserData(gameName.getText());
        //outputController.initialize(stage);

        //2. Controller -> Controller
        //outputController.initialize(gameName.getText());

        //3. Singleton
        //Singleton singleton = Singleton.getInstance();
        //singleton.setUserInput(gameName.getText());
        //outputController.initialize();

        Scene scene = new Scene(root);

        stage.setTitle("PokePedia");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) findDesiredPokemonButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
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

                    setText(pokemon.getName());

                    if(image != null) {
                        imageView.setImage(image);
                        setGraphic(imageView);
                    } else {
                        setGraphic(null);
                    }
                }
            }
        });

        */
    }
}