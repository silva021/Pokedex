package com.silva021.pokedex.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pokemon implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("urlImage")
    @Expose
    private String urlImage;

    @SerializedName("type1")
    @Expose
    private String type1;

    @SerializedName("type2")
    @Expose
    private String type2;

    @SerializedName("generation")
    @Expose
    private int generation;

    @SerializedName("legendary")
    @Expose
    private int legendary;

    @SerializedName("abilities")
    @Expose
    private Abilities abilities;

    public Pokemon(String id, String name, String urlImage, String type1, String type2, int generation, int legendary, Abilities abilities) {
        this.id = id;
        this.name = name;
        this.urlImage = urlImage;
        this.type1 = type1;
        this.type2 = type2;
        this.generation = generation;
        this.legendary = legendary;
        this.abilities = abilities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public int getLegendary() {
        return legendary;
    }

    public void setLegendary(int legendary) {
        this.legendary = legendary;
    }

    public Abilities getAbilities() {
        return abilities;
    }

    public void setAbilities(Abilities abilities) {
        this.abilities = abilities;
    }
}
