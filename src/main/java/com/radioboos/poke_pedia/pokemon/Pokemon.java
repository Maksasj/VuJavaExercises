package com.radioboos.poke_pedia.pokemon;

import com.radioboos.poke_pedia.common.Iconable;

public class Pokemon extends Iconable {
    private PokemonName name;
    private int generation;
    private PokemonStatus status;
    private PokemonType[] type;

    float height;
    float weight;

    private PokemonStatBlock stats;
    private ResistanceStatBlock resistance;

    public Pokemon(PokemonName name, int generation, PokemonStatus status, PokemonType[] type, float height, float weight, PokemonStatBlock stats, ResistanceStatBlock resistance) {
        super("C:\\Programming\\java\\poke_pedia\\src\\main\\resources\\com\\radioboos\\poke_pedia\\icons\\"
                + name.getEngName().toLowerCase().replace(' ', '-')
                + ".jpg");

        this.name = name;
        this.generation = generation;
        this.status = status;
        this.type = type;
        this.height = height;
        this.weight = weight;
        this.stats = stats;
        this.resistance = resistance;
    }

    public String getName() {
        return name.toString();
    }

    public int getGeneration() {
        return generation;
    }

    public PokemonStatus getStatus() {
        return status;
    }

    public PokemonType[] getType() {
        return type;
    }

    public PokemonStatBlock getStats() {
        return stats;
    }
}
