package com.silva021.pokedex.model;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private String name;
    private String id;
    private String urlImage;
    private Stats stats;
    private Abilities abilities;
    private EggGroups eggGroups;

    public Pokemon(String name, String id, String urlImage, Stats stats, Abilities abilities, EggGroups eggGroups) {
        this.name = name;
        this.id = id;
        this.urlImage = urlImage;
        this.stats = stats;
        this.abilities = abilities;
        this.eggGroups = eggGroups;
    }


    public EggGroups getEggGroups() {
        return eggGroups;
    }

    public void setEggGroups(EggGroups eggGroups) {
        this.eggGroups = eggGroups;
    }

    public Abilities getAbilities() {
        return abilities;
    }

    public void setAbilities(Abilities abilities) {
        this.abilities = abilities;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

}
