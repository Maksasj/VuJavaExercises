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

    @Override
    public boolean filter(Pokemon pokemon) {
        boolean inRange = true;

        var pStats = pokemon.getStats();

        inRange &= Utils.inRange(stats.getHp() - scatter, stats.getHp() + scatter, pStats.getHp());
        inRange &= Utils.inRange(stats.getAttack() - scatter, stats.getAttack() + scatter, pStats.getAttack());
        inRange &= Utils.inRange(stats.getDefense() - scatter, stats.getDefense() + scatter, pStats.getDefense());
        inRange &= Utils.inRange(stats.getSpAttack() - scatter, stats.getSpAttack() + scatter, pStats.getSpAttack());
        inRange &= Utils.inRange(stats.getSpDefense() - scatter, stats.getSpDefense() + scatter, pStats.getSpDefense());
        inRange &= Utils.inRange(stats.getSpeed() - scatter, stats.getSpeed() + scatter, pStats.getSpeed());

        return prev.filter(pokemon) && inRange;
    }
}
