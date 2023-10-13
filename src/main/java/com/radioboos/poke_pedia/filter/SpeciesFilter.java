package com.radioboos.poke_pedia.filter;

import com.radioboos.poke_pedia.pokemon.Pokemon;
import com.radioboos.poke_pedia.pokemon.PokemonType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpeciesFilter extends PokemonFilter {
    private final Set<String> species;

    public SpeciesFilter(PokemonFilter base, List<String> species) {
        super(base);

        this.species = new HashSet<>(species);
    }

    @Override
    public boolean filter(Pokemon pokemon) {
        boolean found = false;
        var pTypes = pokemon.getType();

        for (PokemonType pType : pTypes) {
            if (species.contains(pType)) {
                found = true;
                break;
            }
        }

        return prev.filter(pokemon) && (found || species.isEmpty());
    }
}
