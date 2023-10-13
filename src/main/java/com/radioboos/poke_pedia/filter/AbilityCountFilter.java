package com.radioboos.poke_pedia.filter;

import com.radioboos.poke_pedia.pokemon.Pokemon;

public class AbilityCountFilter extends PokemonFilter {
    private final int abilityCount;
    public AbilityCountFilter(PokemonFilter base, int abilityCount) {
        super(base);

        this.abilityCount = abilityCount;
    }

    @Override
    public boolean filter(Pokemon pokemon) {
        // Todo
        System.out.println("Not supported yet");
        return prev.filter(pokemon) && 0 == abilityCount;
    }
}
