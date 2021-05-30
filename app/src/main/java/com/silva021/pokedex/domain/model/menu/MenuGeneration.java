package com.silva021.pokedex.domain.model.menu;

public class MenuGeneration {
    private int idImageGeneration;
    private String generationName;
    private int generation;

    public MenuGeneration(int idImageGeneration, String generationName, int generation) {
        this.idImageGeneration = idImageGeneration;
        this.generationName = generationName;
        this.generation = generation;
    }

    public int getIdImageGeneration() {
        return idImageGeneration;
    }

    public void setIdImageGeneration(int idImageGeneration) {
        this.idImageGeneration = idImageGeneration;
    }

    public String getGenerationName() {
        return generationName;
    }

    public void setGenerationName(String generationName) {
        this.generationName = generationName;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}
