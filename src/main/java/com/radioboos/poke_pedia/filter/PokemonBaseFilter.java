package com.radioboos.poke_pedia.filter;

import com.radioboos.poke_pedia.pokemon.Pokemon;

public class PokemonBaseFilter extends BaseFilter<Pokemon> {
    public PokemonBaseFilter(BaseFilter<Pokemon> prev) {
        super(null);
    }

    @Override
    public boolean filter(Pokemon thing) {
        return true;
    }
}
