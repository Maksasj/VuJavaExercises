package com.radioboos.poke_pedia.controllers;

import com.radioboos.poke_pedia.NameLocalization;
import com.radioboos.poke_pedia.PokePedia;
import com.radioboos.poke_pedia.Pokedex;
import com.radioboos.poke_pedia.filter.*;
import com.radioboos.poke_pedia.pokemon.Pokemon;
import com.radioboos.poke_pedia.pokemon.PokemonStatBlock;
import com.radioboos.poke_pedia.pokemon.PokemonStatus;
import com.radioboos.poke_pedia.pokemon.PokemonType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    // General information
    @FXML protected CheckBox nameFilterCheckBox;
    @FXML protected TextField nameFilterTextField;
    @FXML protected Button nameFilterClearButton;

    @FXML protected CheckBox generationCheckBox;
    @FXML protected Spinner<Integer> generationSpinner;

    @FXML protected CheckBox statusCheckBox;
    @FXML protected ChoiceBox<PokemonStatus> statusChoiceBox;

    // Name localization
    @FXML protected ChoiceBox<NameLocalization> nameLocalizationChoiceBox;

    // Extra information
    // First tab
    @FXML protected CheckBox totalStatPointFilterCheckBox;
    @FXML protected Spinner<Double> minTotalStatPointsSpinner;
    @FXML protected Spinner<Double> maxTotalStatPointsSpinner;

    @FXML protected CheckBox pokemonMetricsFilterCheckBox;
    @FXML protected Spinner<Double> minWeightSpinner;
    @FXML protected Spinner<Double> minHeightSpinner;

    @FXML protected CheckBox abilityCountFilterCheckBox;
    @FXML protected Spinner<Integer> abilityCountSpinner;

    // Second tab
    @FXML protected CheckBox typeFilterCheckBox;
    @FXML protected ListView<PokemonType> typeListView;
    @FXML protected ChoiceBox<PokemonType> typeChoiceBox;
    @FXML protected Button addTypeButton;
    @FXML protected Button clearTypeButton;
    @FXML protected CheckBox speciesFilterCheckBox;
    @FXML protected ListView<String> speciesListView;
    @FXML protected TextField speciesTextField;
    @FXML protected Button addSpeciesButton;
    @FXML protected Button clearSpeciesButton;

    // Stats filter
    @FXML protected CheckBox statsFilterCheckBox;
    @FXML protected Slider minHpSlider;
    @FXML protected Slider minAttackSlider;
    @FXML protected Slider minDefenseSlider;
    @FXML protected Slider minSpAttackSlider;
    @FXML protected Slider minSpDefenseSlider;
    @FXML protected Slider minSpeedSlider;
    @FXML protected Spinner<Integer> statScatterSpinner;
    @FXML protected Text totalPointsText;
    @FXML protected Button pointsResetButton;
    @FXML protected Button scatterResetButton;

    @FXML private Button findDesiredPokemonButton;
    @FXML private Button resetToDefaultsButton;
    @FXML private Text pokemonPassFiltersText;

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

    private void onAnyAction() {
        var filter = assembleFilter();

        updatePokemonPassFilterText(filter);
    }

    private void updatePokemonPassFilterText(PokemonFilter filter) {
        var pokemons = new ArrayList<Pokemon>();

        for(var pokemon : Pokedex.getInstance().getPokemons()) {
            if(filter.filter(pokemon)) {
                pokemons.add(pokemon);
            }
        }

        pokemonPassFiltersText.setText("Only " + pokemons.size() + " pass these filters");
    }

    private PokemonFilter assembleFilter() {
        var filter = new PokemonFilter();

        if(nameFilterCheckBox.isSelected()) {
            String name = nameFilterTextField.getText();

            filter = new NameFilter(filter, name);
        }

        if(generationCheckBox.isSelected()) {
            Integer generation = generationSpinner.getValue();

            filter = new GenerationFilter(filter, generation);
        }

        if(statusCheckBox.isSelected()) {
            PokemonStatus status = statusChoiceBox.getValue();

            filter = new StatusFilter(filter, status);
        }

        if(totalStatPointFilterCheckBox.isSelected()) {
            var min = minTotalStatPointsSpinner.getValue();
            var max = maxTotalStatPointsSpinner.getValue();

            filter = new TotalStatPointsFilter(filter, min.floatValue(), max.floatValue());
        }

        if(pokemonMetricsFilterCheckBox.isSelected()) {
            var minWeight = minWeightSpinner.getValue();
            var minHeight = minHeightSpinner.getValue();

            filter = new MetricsFilter(filter, minWeight.floatValue(), minHeight.floatValue());
        }

        if(abilityCountFilterCheckBox.isSelected()) {
            var abilityCount = abilityCountSpinner.getValue();

            filter = new AbilityCountFilter(filter, abilityCount.intValue());
        }

        if(typeFilterCheckBox.isSelected()) {
            var types = typeListView.getSelectionModel().getSelectedItems();

            filter = new TypeFilter(filter, types);
        }

        if(speciesFilterCheckBox.isSelected()) {
            var species = speciesListView.getSelectionModel().getSelectedItems();

            filter = new SpeciesFilter(filter, species);
        }

        if(statsFilterCheckBox.isSelected()) {
            int hp = (int) (minHpSlider.getValue() * 255.0f);
            int attack = (int) (minAttackSlider.getValue() * 255.0f);
            int defense = (int) (minDefenseSlider.getValue() * 255.0f);
            int spAttack = (int) (minSpAttackSlider.getValue() * 255.0f);
            int spDefense = (int) (minSpDefenseSlider.getValue() * 255.0f);
            int speed = (int) (minSpeedSlider.getValue() * 255.0f);

            int valueScatter = statScatterSpinner.getValue();

            var stats = new PokemonStatBlock(hp, attack, defense, spAttack, spDefense, speed, 0, 0, 0);

            filter = new StatsFilter(filter, stats, valueScatter);
        }

        return filter;
    }

    @FXML
    protected void onEnableNameFilter() {
        boolean value = !nameFilterCheckBox.isSelected();

        nameFilterTextField.setDisable(value);
        nameFilterClearButton.setDisable(value);

        onAnyAction();
    }

    @FXML
    protected void onEnableGenerationFilter() {
        boolean value = !generationCheckBox.isSelected();

        generationSpinner.setDisable(value);

        onAnyAction();
    }

    @FXML
    protected void onEnableStatusFilter() {
        boolean value = !statusCheckBox.isSelected();

        statusChoiceBox.setDisable(value);

        onAnyAction();
    }

    @FXML
    protected void onEnableTotalPointsFilter() {
        boolean value = !totalStatPointFilterCheckBox.isSelected();

        minTotalStatPointsSpinner.setDisable(value);
        maxTotalStatPointsSpinner.setDisable(value);

        onAnyAction();
    }

    @FXML
    protected void onEnablePokemonMetricsFilter() {
        boolean value = !pokemonMetricsFilterCheckBox.isSelected();

        minWeightSpinner.setDisable(value);
        minHeightSpinner.setDisable(value);

        onAnyAction();
    }

    @FXML
    protected void onEnableAbilityCountFilter() {
        boolean value = !abilityCountFilterCheckBox.isSelected();

        abilityCountSpinner.setDisable(value);

        onAnyAction();
    }

    @FXML
    protected void onEnableTypeFilter() {
        boolean value = !typeFilterCheckBox.isSelected();

        typeListView.setDisable(value);
        typeChoiceBox.setDisable(value);
        addTypeButton.setDisable(value);
        clearTypeButton.setDisable(value);

        onAnyAction();
    }

    @FXML
    protected void onEnableSpeciesFilter() {
        boolean value = !speciesFilterCheckBox.isSelected();

        speciesListView.setDisable(value);
        addSpeciesButton.setDisable(value);
        speciesTextField.setDisable(value);
        clearSpeciesButton.setDisable(value);

        onAnyAction();
    }

    @FXML
    protected void onEnableStatsFilter() {
        boolean value = !statsFilterCheckBox.isSelected();

        minHpSlider.setDisable(value);
        minAttackSlider.setDisable(value);
        minDefenseSlider.setDisable(value);
        minSpAttackSlider.setDisable(value);
        minSpDefenseSlider.setDisable(value);
        minSpeedSlider.setDisable(value);
        statScatterSpinner.setDisable(value);
        totalPointsText.setDisable(value);
        pointsResetButton.setDisable(value);
        scatterResetButton.setDisable(value);

        onAnyAction();
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
}