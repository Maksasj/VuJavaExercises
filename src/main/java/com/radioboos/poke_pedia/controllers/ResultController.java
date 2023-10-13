package com.radioboos.poke_pedia.controllers;

import com.radioboos.poke_pedia.PokePedia;
import com.radioboos.poke_pedia.Pokedex;
import com.radioboos.poke_pedia.common.PokemonListCell;
import com.radioboos.poke_pedia.pokemon.Pokemon;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResultController implements Initializable {
    @FXML protected ImageView pokemonIconImageView;
    @FXML protected Text pokemonNameText;



    @FXML
    protected Button goBackButton;
    @FXML
    protected ListView<Pokemon> foundPokemonListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        foundPokemonListView.setCellFactory(param -> new PokemonListCell());

        ObservableList<Pokemon> items = FXCollections.observableArrayList (Pokedex.getInstance().getPokemons());
        foundPokemonListView.setItems(items);

        foundPokemonListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pokemon>() {
            @Override
            public void changed(ObservableValue<? extends Pokemon> observable, Pokemon oldValue, Pokemon newValue) {
                updateSelectedPokemonInfo(newValue);
            }
        });
    }

    private void updateSelectedPokemonInfo(Pokemon pokemon) {
        System.out.println(pokemon.getName());
    }

    @FXML
    protected void onGoBack() throws IOException {
        {
            FXMLLoader fxmlLoader = new FXMLLoader(PokePedia.class.getResource("pokePediaMain.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setTitle("PokePedia");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
        }

        {
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            stage.close();
        }
    }
}