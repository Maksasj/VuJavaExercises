package com.radioboos.poke_pedia.filter;

import com.radioboos.poke_pedia.common.Utils;
import com.radioboos.poke_pedia.pokemon.Pokemon;
import com.radioboos.poke_pedia.pokemon.PokemonStatBlock;
import com.radioboos.poke_pedia.pokemon.PokemonType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StatsFilter extends PokemonFilter {
    private final PokemonStatBlock stats;
    private final int scatter;

    public StatsFilter(PokemonFilter base, PokemonStatBlock stats, int scatter) {
        super(base);

        this.stats = stats;
        this.scatter = scatter;
    }

    private boolean inScatterRange(float value, float scat, float testedValue) {
        return Utils.inRange(value - scatter, Float.MAX_VALUE, testedValue) && Utils.inRange(value + scatter, Float.MAX_VALUE, testedValue);
    }

    @Override
    public boolean filter(Pokemon pokemon) {
        boolean inRange = true;

        var pStats = pokemon.getStats();

        inRange &= inScatterRange(stats.getHp(), scatter, pStats.getHp());
        inRange &= inScatterRange(stats.getAttack(), scatter, pStats.getAttack());
        inRange &= inScatterRange(stats.getDefense(), scatter, pStats.getDefense());
        inRange &= inScatterRange(stats.getSpAttack(), scatter, pStats.getSpAttack());
        inRange &= inScatterRange(stats.getSpDefense(), scatter, pStats.getSpDefense());
        inRange &= inScatterRange(stats.getSpeed(), scatter, pStats.getSpeed());

        return prev.filter(pokemon) && inRange;
    }
}
