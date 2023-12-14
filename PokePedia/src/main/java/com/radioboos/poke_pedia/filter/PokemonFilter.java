/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.radioboos.poke_pedia.filter;

import com.radioboos.poke_pedia.pokemon.Pokemon;

public class PokemonFilter extends BaseFilter<Pokemon> {
    public PokemonFilter() {
        super(null);
    }
    public PokemonFilter(PokemonFilter filter) {
        super(filter);
    }

    @Override
    public boolean filter(Pokemon pokemon) {
        return true;
    }
}
