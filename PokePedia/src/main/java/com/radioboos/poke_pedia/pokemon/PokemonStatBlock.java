/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.radioboos.poke_pedia.pokemon;

public class PokemonStatBlock {
    private final float hp;
    private final float attack;
    private final float defense;
    private final float spAttack;
    private final float spDefense;
    private final float speed;
    private final float catchRate;
    private final float baseFriendship;
    private final float baseExperience;

    public PokemonStatBlock(float hp, float attack, float defense, float spAttack, float spDefense, float speed, float catchRate, float baseFriendship, float baseExperience) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spAttack = spAttack;
        this.spDefense = spDefense;
        this.speed = speed;
        this.catchRate = catchRate;
        this.baseFriendship = baseFriendship;
        this.baseExperience = baseExperience;
    }

    public float getHp() {
        return hp;
    }

    public float getAttack() {
        return attack;
    }

    public float getDefense() {
        return defense;
    }

    public float getSpAttack() {
        return spAttack;
    }

    public float getSpDefense() {
        return spDefense;
    }

    public float getSpeed() {
        return speed;
    }

    public float getCatchRate() {
        return catchRate;
    }

    public float getBaseFriendship() {
        return baseFriendship;
    }

    public float getBaseExperience() {
        return baseExperience;
    }

    public float getTotalPoints() {
        return getHp() + getAttack() + getDefense() + getSpAttack() + getSpDefense() + getSpeed();
    }
}
