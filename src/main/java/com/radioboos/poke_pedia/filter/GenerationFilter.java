package com.radioboos.poke_pedia.filter;

import com.radioboos.poke_pedia.common.Utils;
import com.radioboos.poke_pedia.pokemon.Pokemon;

public class GenerationFilter extends BaseFilter<Pokemon> {
    private final int generation;
    GenerationFilter(BaseFilter<Pokemon> base, int generation) {
        super(base);

        this.generation = generation;
    }

    @Override
    public boolean filter(Pokemon pokemon) {
        return prev.filter(pokemon) && pokemon.getGeneration() == generation;
    }
}
