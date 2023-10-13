package com.radioboos.poke_pedia.filter;

import com.radioboos.poke_pedia.pokemon.Pokemon;
import com.radioboos.poke_pedia.pokemon.PokemonType;

public class TypeFilter extends PokemonFilter {
    private final PokemonType type;

    public TypeFilter(PokemonFilter base, PokemonType type) {
        super(base);

        this.type = type;
    }

    @Override
    public boolean filter(Pokemon pokemon) {
        boolean found = false;
        var types = pokemon.getType();

        for(int i = 0; i < types.length; ++i) {
            if(types[i] == type) {
                found = true;
                break;
            }
        }

        return prev.filter(pokemon) && found;
    }
}
