package com.radioboos.poke_pedia;

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

    private Set<String> status = new HashSet<String>();

    public Pokemon createPokemon(String[] data) {
        if(data.length != 51)
            return null;

        status.add(data[9]);
        status.add(data[10]);

        return new Pokemon(Integer.parseInt(data[1]), data[2], data[4]);
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

        for(var s : status) {
            System.out.println(s);
        }
    }
}
