package com.radioboos.poke_pedia;

public enum NameLocalization {
    ENGLISH,
    GERMAN,
    JAPANESE;

    static public NameLocalization DEFAULT_NAME_LOCALIZATION = ENGLISH;

    @Override
    public String toString() {
        return switch (this) {
            case ENGLISH -> "English";
            case GERMAN -> "German";
            case JAPANESE -> "Japanese";
        };
    }
}
