/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.radioboos.poke_pedia.pokemon;

import com.radioboos.poke_pedia.NameLocalization;

public class PokemonName {
    private final String engName;
    private final String gerName;
    private final String japName;

    public PokemonName(String engName, String gerName, String japName) {
        this.engName = engName;
        this.gerName = gerName;
        this.japName = japName;
    }

    public String getEngName() {
        return engName;
    }

    public String getGerName() {
        return gerName;
    }

    public String getJapName() {
        return japName;
    }

    @Override
    public String toString() {
        return switch (NameLocalization.DEFAULT_NAME_LOCALIZATION) {
            case ENGLISH -> this.engName;
            case GERMAN -> this.gerName;
            case JAPANESE -> this.japName;
        };
    }
}
