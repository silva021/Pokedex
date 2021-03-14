package com.silva021.pokedex.model;

import java.io.Serializable;

public class EggGroup implements Serializable {
    String name;

    public EggGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
