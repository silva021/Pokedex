package com.silva021.pokedex.model;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private String name;
    private String id;

    private String urlImage;

    public Pokemon(String name, String id, String urlImage) {
        this.name = name;
        this.id = id;
        this.urlImage = urlImage;
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
