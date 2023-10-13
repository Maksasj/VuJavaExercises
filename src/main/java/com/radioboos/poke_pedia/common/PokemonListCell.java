package com.radioboos.poke_pedia.common;

import com.radioboos.poke_pedia.PokePedia;
import com.radioboos.poke_pedia.pokemon.Pokemon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class PokemonListCell extends ListCell<Pokemon> {
    @FXML
    public AnchorPane thingAnchorPane;
    @FXML
    public ImageView pokemonIcon;
    @FXML public Text pokemonNameText;
    @FXML public Text pokemonGenerationText;
    @FXML public Text pokemonStatusText;
    @FXML public Text pokemonTypeText;
    @FXML public Text pokemonTotalPointsText;
    @FXML public Text pokemonSpeciesText;

    private FXMLLoader loader;

    public PokemonListCell() {
        super();
    }

    @Override
    public void updateItem(Pokemon pokemon, boolean empty) {
        super.updateItem(pokemon, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (loader == null) {
                loader = new FXMLLoader(PokePedia.class.getResource("pokePediaPokemonListCell.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            var icon = pokemon.getImage();
            if(icon != null) {
                pokemonIcon.setImage(icon);
            }

            pokemonNameText.setText(pokemon.getLocName());
            pokemonGenerationText.setText("Generation " + pokemon.getGeneration());
            pokemonStatusText.setText("Status " + pokemon.getStatus().toString());

            String typeString = new String("Type ");
            var pokemonType = pokemon.getType();
            for(var i = 0; i < pokemonType.length; ++i) {
                typeString += pokemonType[i].toString() + " ";
            }

            pokemonTypeText.setText(typeString);
            pokemonTotalPointsText.setText("Total Points " + String.valueOf((int) pokemon.getStats().getTotalPoints()));

            setGraphic(thingAnchorPane);
        }
    }
}
