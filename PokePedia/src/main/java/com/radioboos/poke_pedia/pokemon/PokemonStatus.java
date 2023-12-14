/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.radioboos.poke_pedia.pokemon;

public enum PokemonStatus {
    LEGENDARY,
    NORMAL,
    MYTHICAL,
    SUB_LEGENDARY;

    static public PokemonStatus fromString(String input) {
        return switch (input) {
            case "Legendary" -> LEGENDARY;
            case "Mythical" -> MYTHICAL;
            case "Sub Legendary" -> SUB_LEGENDARY;
            default -> NORMAL;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case LEGENDARY -> "Legendary";
            case MYTHICAL -> "Mythical";
            case SUB_LEGENDARY -> "Sub Legendary";
            case NORMAL -> "Normal";
        };
    }
}
