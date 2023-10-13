package com.radioboos.poke_pedia;

public class PokemonStatBlock {
    private final int hp;
    private final int attack;
    private final int defense;
    private final int spAttack;
    private final int spDefense;
    private final int speed;
    private final int catch_rate;
    private final int baseFriendship;
    private final int baseExperience;

    public PokemonStatBlock(int hp, int attack, int defense, int spAttack, int spDefense, int speed, int catch_rate, int baseFriendship, int baseExperience) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spAttack = spAttack;
        this.spDefense = spDefense;
        this.speed = speed;
        this.catch_rate = catch_rate;
        this.baseFriendship = baseFriendship;
        this.baseExperience = baseExperience;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpAttack() {
        return spAttack;
    }

    public int getSpDefense() {
        return spDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCatchRate() {
        return catch_rate;
    }

    public int getBaseFriendship() {
        return baseFriendship;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public int getTotalPoints() {
        return getHp() + getAttack() + getDefense() + getSpAttack() + getSpDefense() + getSpeed() + getCatchRate() + getBaseFriendship() + getBaseExperience();
    }
}
