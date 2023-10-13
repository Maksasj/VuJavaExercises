package com.radioboos.poke_pedia.pokemon;

public enum PokemonType {
    WATER,
    FIGHTING,
    ROCK,
    GROUND,
    ELECTRIC,
    ICE,
    GRASS,
    NORMAL,
    STEEL,
    DRAGON,
    FLYING,
    PSYCHIC,
    BUG,
    DARK,
    GHOST,
    FIRE,
    POISON,
    FAIRY;

    static public PokemonType fromString(String input) {
        return switch (input) {
            case "Water" -> WATER;
            case "Fighting" -> FIGHTING;
            case "Rock" -> ROCK;
            case "Ground" -> GROUND;
            case "Electric" -> ELECTRIC;
            case "Ice" -> ICE;
            case "Grass" -> GRASS;
            case "Normal" -> NORMAL;
            case "Steel" -> STEEL;
            case "Dragon" -> DRAGON;
            case "Flying" -> FLYING;
            case "Psychic" -> PSYCHIC;
            case "Bug" -> BUG;
            case "Dark" -> DARK;
            case "Ghost" -> GHOST;
            case "Fire" -> FIRE;
            case "Poison" -> POISON;
            case "Fairy" -> FAIRY;
            default -> NORMAL;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case WATER -> "Water";
            case FIGHTING -> "Fighting";
            case ROCK -> "Rock";
            case GROUND -> "Ground";
            case ELECTRIC -> "Electric";
            case ICE -> "Ice";
            case GRASS -> "Grass";
            case NORMAL -> "Normal";
            case STEEL -> "Steel";
            case DRAGON -> "Dragon";
            case FLYING -> "Flying";
            case PSYCHIC -> "Psychic";
            case BUG -> "Bug";
            case DARK -> "Dark";
            case GHOST -> "Ghost";
            case FIRE -> "Fire";
            case POISON -> "Poison";
            case FAIRY -> "Fairy";
        };
    }
}
