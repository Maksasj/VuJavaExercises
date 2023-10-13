package com.radioboos.poke_pedia.filter;

import com.radioboos.poke_pedia.pokemon.Pokemon;
import com.radioboos.poke_pedia.pokemon.PokemonStatus;

public class StatusFilter extends BaseFilter<Pokemon> {
    private final PokemonStatus status;

    StatusFilter(BaseFilter<Pokemon> base, PokemonStatus status) {
        super(base);

        this.status = status;
    }

    @Override
    public boolean filter(Pokemon pokemon) {
        return prev.filter(pokemon) && pokemon.getStatus() == status;
    }
}
