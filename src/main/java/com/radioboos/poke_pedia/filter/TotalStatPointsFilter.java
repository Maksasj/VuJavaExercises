package com.radioboos.poke_pedia.filter;

import com.radioboos.poke_pedia.common.Utils;
import com.radioboos.poke_pedia.pokemon.Pokemon;

public class TotalStatPointsFilter extends PokemonFilter {
    private final float minValue;
    private final float maxValue;

    public TotalStatPointsFilter(PokemonFilter base, float minValue, float maxValue) {
        super(base);

        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public boolean filter(Pokemon pokemon) {
        return prev.filter(pokemon) && Utils.inRange(minValue, maxValue, pokemon.getStats().getTotalPoints());
    }
}
