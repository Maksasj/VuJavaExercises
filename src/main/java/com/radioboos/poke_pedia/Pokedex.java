package com.radioboos.poke_pedia;

import com.radioboos.poke_pedia.common.Utils;
import com.radioboos.poke_pedia.pokemon.*;

import java.io.FileReader;
import java.io.BufferedReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pokedex {
    static private Pokedex INSTANCE;

    private List<Pokemon> pokemons;

    private Pokedex() {
        pokemons = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public static Pokedex getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Pokedex();
        }

        return INSTANCE;
    }

    public void clearPokedex() {
        pokemons.clear();
    }

    // private Set<String> status = new HashSet<String>();

    public Pokemon createPokemon(String[] data) {

        if(data.length != 51)
            return null;

        PokemonName name = new PokemonName(data[2], data[3], data[4]);
        int generation = Integer.parseInt(data[5]);
        PokemonStatus status = PokemonStatus.fromString(data[6]);

        PokemonType[] type = {
          PokemonType.fromString(data[9])
        };

        float height = Utils.parseFloat(data[11], 0);
        float weight = Utils.parseFloat(data[12], 0);

        PokemonStatBlock stats = new PokemonStatBlock(
            Utils.parseFloat(data[18], 0),
            Utils.parseFloat(data[19], 0),
            Utils.parseFloat(data[20], 0),
            Utils.parseFloat(data[21], 0),
            Utils.parseFloat(data[22], 0),
            Utils.parseFloat(data[23], 0),
            Utils.parseFloat(data[24], 0),
            Utils.parseFloat(data[25], 0),
            Utils.parseFloat(data[26], 0)
        );

        ResistanceStatBlock resistance = new ResistanceStatBlock(
                Utils.parseFloat(data[34], 0),
                Utils.parseFloat(data[35], 0),
                Utils.parseFloat(data[36], 0),
                Utils.parseFloat(data[37], 0),
                Utils.parseFloat(data[38], 0),
                Utils.parseFloat(data[39], 0),
                Utils.parseFloat(data[40], 0),
                Utils.parseFloat(data[41], 0),
                Utils.parseFloat(data[42], 0),
                Utils.parseFloat(data[43], 0),
                Utils.parseFloat(data[44], 0),
                Utils.parseFloat(data[45], 0),
                Utils.parseFloat(data[46], 0),
                Utils.parseFloat(data[47], 0),
                Utils.parseFloat(data[48], 0),
                Utils.parseFloat(data[49], 0),
                Utils.parseFloat(data[50], 0)
        );

        return new Pokemon(name, generation, status, type, height, weight, stats, resistance);
    }

    public void loadPokedexFromFile(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                var data = line.split(",");
                var pokemon = createPokemon(data);

                if(pokemon == null)
                    continue;

                addPokemon(pokemon);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
