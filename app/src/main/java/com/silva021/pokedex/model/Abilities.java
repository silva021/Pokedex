package com.silva021.pokedex.model;

import java.io.Serializable;
import java.util.List;

public class Abilities implements Serializable {
    List<Ability> list;

    public Abilities(List<Ability> list) {
        this.list = list;
    }

    public List<Ability> getList() {
        return list;
    }

    public void setList(List<Ability> list) {
        this.list = list;
    }
}
