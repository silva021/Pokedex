package com.silva021.pokedex.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Abilities implements Serializable {

    @SerializedName("hp")
    @Expose
    private int HP;

    @SerializedName("attack")
    @Expose
    private int attack;

    @SerializedName("defense")
    @Expose
    private int defense;

    @SerializedName("spkAttack")
    @Expose
    private int specialAttack;

    @SerializedName("spkDefense")
    @Expose
    private int specialDefense;

    @SerializedName("speed")
    @Expose
    private int speed;

    @SerializedName("total")
    @Expose
    private int total;

    public Abilities(int HP, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.HP = HP;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.total = HP + attack + defense + specialAttack + specialDefense + speed;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
