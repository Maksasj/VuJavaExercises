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
        int foundAbilityCount = 0;

        for(var ability : pokemon.getAbilities().getAbilityNames()) {
            if(!ability.contentEquals("")) {
                ++foundAbilityCount;
            }
        }

        return prev.filter(pokemon) && 0 == abilityCount;
    }
}
