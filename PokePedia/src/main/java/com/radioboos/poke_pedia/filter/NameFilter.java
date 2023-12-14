/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.radioboos.poke_pedia.filter;

import com.radioboos.poke_pedia.pokemon.Pokemon;

public class NameFilter extends PokemonFilter {
    private final String name;

    public NameFilter(PokemonFilter base, String name) {
        super(base);

        this.name = name;
    }

    @Override
    public boolean filter(Pokemon pokemon) {
        return prev.filter(pokemon) && pokemon.getLocName().contentEquals(name);
    }
}
