/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.radioboos.poke_pedia.filter;

import com.radioboos.poke_pedia.common.Utils;
import com.radioboos.poke_pedia.pokemon.Pokemon;

public class MetricsFilter extends PokemonFilter {
    private final float minWeight;
    private final float minHeight;

    public MetricsFilter(PokemonFilter base, float minWeight, float minHeight) {
        super(base);

        this.minWeight = minWeight;
        this.minHeight = minHeight;
    }

    @Override
    public boolean filter(Pokemon pokemon) {
        return prev.filter(pokemon) && (pokemon.getHeight() > minHeight) && (pokemon.getWeight() > minWeight);
    }
}
