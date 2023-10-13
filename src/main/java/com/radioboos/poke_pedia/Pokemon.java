package com.radioboos.poke_pedia;

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

    public Pokemon(int id, String engName, String jpnName) {
        super("C:\\Programming\\java\\poke_pedia\\src\\main\\resources\\com\\radioboos\\poke_pedia\\icons\\"
                + engName.toLowerCase().replace(' ', '-')
                + ".jpg");
    }

    public String getName() {
        return name.toString();
    }
}
