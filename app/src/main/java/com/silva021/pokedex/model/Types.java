package com.silva021.pokedex.model;

import java.io.Serializable;
import java.util.List;

public class Types implements Serializable {
    List<Type> list;

    public Types(List<Type> list) {
        this.list = list;
    }

    public List<Type> getList() {
        return list;
    }

    public void setList(List<Type> list) {
        this.list = list;
    }
}
