package com.silva021.pokedex.model;

import java.io.Serializable;

public class Ability implements Serializable {
    String name;

    public Ability(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
