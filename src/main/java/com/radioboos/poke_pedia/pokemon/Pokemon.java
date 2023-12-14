/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.radioboos.poke_pedia.pokemon;

import com.radioboos.poke_pedia.common.Iconable;

public class Pokemon extends Iconable {
    private PokemonName name;
    private int generation;
    private PokemonStatus status;
    private PokemonType[] type;
    private PokemonAbilities abilities;

    float height;
    float weight;

    private PokemonStatBlock stats;
    private ResistanceStatBlock resistance;

    public Pokemon(PokemonName name, int generation, PokemonStatus status, PokemonType[] type, PokemonAbilities abilities, float height, float weight, PokemonStatBlock stats, ResistanceStatBlock resistance) {
        super("C:\\Programming\\java\\poke_pedia\\src\\main\\resources\\com\\radioboos\\poke_pedia\\icons\\"
                + name.getEngName().toLowerCase().replace(' ', '-')
                + ".jpg");

        this.name = name;
        this.generation = generation;
        this.status = status;
        this.type = type;
        this.abilities = abilities;

        this.height = height;
        this.weight = weight;
        this.stats = stats;
        this.resistance = resistance;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public PokemonName getName() {
        return name;
    }

    public String getLocName() {
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

    public PokemonAbilities getAbilities() {
        return abilities;
    }

    public PokemonStatBlock getStats() {
        return stats;
    }
}
