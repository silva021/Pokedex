package com.silva021.pokedex.model;

import java.io.Serializable;
import java.util.List;

public class EggGroups implements Serializable {
    List<EggGroup> list;

    public EggGroups(List<EggGroup> list) {
        this.list = list;
    }

    public List<EggGroup> getList() {
        return list;
    }

    public void setList(List<EggGroup> list) {
        this.list = list;
    }
}
