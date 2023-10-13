package com.radioboos.poke_pedia.controllers;

import com.radioboos.poke_pedia.PokePedia;
import com.radioboos.poke_pedia.Pokedex;
import com.radioboos.poke_pedia.common.PokemonListCell;
import com.radioboos.poke_pedia.filter.BaseFilter;
import com.radioboos.poke_pedia.filter.GenerationFilter;
import com.radioboos.poke_pedia.filter.PokemonFilter;
import com.radioboos.poke_pedia.filter.StatusFilter;
import com.radioboos.poke_pedia.pokemon.Pokemon;
import com.radioboos.poke_pedia.pokemon.PokemonStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResultController implements Initializable {
    @FXML protected ImageView pokemonIconImageView;

    // General
    @FXML protected Text pokemonNameText;
    @FXML protected Text pokemonStatusText;
    @FXML protected Text pokemonGenerationText;
    @FXML protected Text pokemonTypeText;
    @FXML protected Text pokemonSpeciesText;

    // Localization
    @FXML protected Text pokemonNameEnglishText;
    @FXML protected Text pokemonNameGermanText;
    @FXML protected Text pokemonNameJapaneseText;

    // Metrics
    @FXML protected Text pokemonWeightText;
    @FXML protected Text pokemonHeightText;

    // Growth
    @FXML protected Text pokemonGrowthRateText;
    @FXML protected Text pokemonEggTypeText;
    @FXML protected Text pokemonEggTimeText;

    // Stats
    @FXML protected Text pokemonStatsHpText;
    @FXML protected Text pokemonStatsAttackText;
    @FXML protected Text pokemonStatsDefenseText;
    @FXML protected Text pokemonStatsSpAttackText;
    @FXML protected Text pokemonStatsSpDefenseText;
    @FXML protected Text pokemonStatsSpeedText;
    @FXML protected ProgressBar pokemonStatsHpProgressBar;
    @FXML protected ProgressBar pokemonStatsAttackProgressBar;
    @FXML protected ProgressBar pokemonStatsDefenseProgressBar;
    @FXML protected ProgressBar pokemonStatsSpAttackProgressBar;
    @FXML protected ProgressBar pokemonStatsSpDefenseProgressBar;
    @FXML protected ProgressBar pokemonStatsSpeedProgressBar;

    @FXML protected Text pokemonTotalPointsText;

    // Battle
    @FXML protected Text pokemonAbility1Text;
    @FXML protected Text pokemonAbility2Text;
    @FXML protected Text pokemonHidddenAbilityText;


    @FXML
    protected Button goBackButton;
    @FXML
    protected ListView<Pokemon> foundPokemonListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        foundPokemonListView.setCellFactory(param -> new PokemonListCell());

        foundPokemonListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pokemon>() {
            @Override
            public void changed(ObservableValue<? extends Pokemon> observable, Pokemon oldValue, Pokemon newValue) {
                updateSelectedPokemonInfo(newValue);
            }
        });

        var filter = new PokemonFilter();
        filter = new GenerationFilter(filter, 4);
        filter = new StatusFilter(filter, PokemonStatus.LEGENDARY);

        fillListView(filter);
    }

    public void fillListView(BaseFilter<Pokemon> filter) {
        var pokemons = new ArrayList<Pokemon>();

        for(var pokemon : Pokedex.getInstance().getPokemons()) {
            if(filter.filter(pokemon)) {
                pokemons.add(pokemon);
            }
        }

        var items = FXCollections.observableArrayList(pokemons);
        foundPokemonListView.setItems(items);
    }

    private void updateSelectedPokemonInfo(Pokemon pokemon) {
        pokemonIconImageView.setImage(pokemon.getImage());

        pokemonNameText.setText(pokemon.getLocName());
        pokemonStatusText.setText("Status: " + pokemon.getStatus().toString());
        pokemonGenerationText.setText("Generation: " + String.valueOf(pokemon.getGeneration()));

        String typeString = new String("Type: ");
        var pokemonType = pokemon.getType();
        for(var i = 0; i < pokemonType.length; ++i) {
            typeString += pokemonType[i].toString() + " ";
        }

        pokemonTypeText.setText(typeString);
        pokemonSpeciesText.setText("Species: Seed Pokemon");

        // Localization
        pokemonNameEnglishText.setText(pokemon.getName().getEngName());
        pokemonNameGermanText.setText(pokemon.getName().getGerName());
        pokemonNameJapaneseText.setText(pokemon.getName().getJapName());

        // Metrics
        pokemonWeightText.setText("Weight: " + pokemon.getWeight()+ "kg");
        pokemonHeightText.setText("Height: " + pokemon.getHeight()+ "m");

        // Growth
        pokemonGrowthRateText.setText("Rate: Medium Slow");
        pokemonEggTypeText.setText("Egg: Grass");
        pokemonEggTimeText.setText("Egg time: 20");

        // Stats
        pokemonStatsHpText.setText(String.valueOf((int )pokemon.getStats().getHp()));
        pokemonStatsAttackText.setText(String.valueOf((int )pokemon.getStats().getAttack()));
        pokemonStatsDefenseText.setText(String.valueOf((int )pokemon.getStats().getDefense()));
        pokemonStatsSpAttackText.setText(String.valueOf((int )pokemon.getStats().getSpAttack()));
        pokemonStatsSpDefenseText.setText(String.valueOf((int )pokemon.getStats().getSpDefense()));
        pokemonStatsSpeedText.setText(String.valueOf((int )pokemon.getStats().getSpeed()));

        pokemonStatsHpProgressBar.setProgress(pokemon.getStats().getHp() / 255.0f);
        pokemonStatsAttackProgressBar.setProgress(pokemon.getStats().getAttack() / 255.0f);
        pokemonStatsDefenseProgressBar.setProgress(pokemon.getStats().getDefense() / 255.0f);
        pokemonStatsSpAttackProgressBar.setProgress(pokemon.getStats().getSpAttack() / 255.0f);
        pokemonStatsSpDefenseProgressBar.setProgress(pokemon.getStats().getSpDefense() / 255.0f);
        pokemonStatsSpeedProgressBar.setProgress(pokemon.getStats().getSpeed() / 255.0f);

        pokemonTotalPointsText.setText("Total points: " + ((int )pokemon.getStats().getTotalPoints()));

        // Battle
        pokemonAbility1Text.setText("Ability 1: Overgrow");
        pokemonAbility2Text.setText("Ability 2: None");
        pokemonHidddenAbilityText.setText("Hidden Ability: Chlorophyll");
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