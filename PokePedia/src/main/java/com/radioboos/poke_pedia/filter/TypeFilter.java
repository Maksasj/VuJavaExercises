/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.radioboos.poke_pedia.filter;

import com.radioboos.poke_pedia.pokemon.Pokemon;
import com.radioboos.poke_pedia.pokemon.PokemonType;

import java.util.*;

public class TypeFilter extends PokemonFilter {
    private final Set<PokemonType> types;

    public TypeFilter(PokemonFilter base, List<PokemonType> types) {
        super(base);

        this.types = new HashSet<>(types);
    }

    @Override
    public boolean filter(Pokemon pokemon) {
        boolean found = false;
        var pTypes = pokemon.getType();

        for (PokemonType pType : pTypes) {
            if (types.contains(pType)) {
                found = true;
                break;
            }
        }

        return prev.filter(pokemon) && (found || types.isEmpty());
    }
}
