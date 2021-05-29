package com.silva021.pokedex.domain.model.menu;

import android.app.Activity;

public class Menu {
    private String textItem;
    private int colorBackground;
    private int colorPokeball;
    private int colorPokeballDetails;
    private Class<?> activityClass;

    public Menu(String textItem, int colorBackground, int colorPokeball, int colorPokeballDetails, Class<?> activityClass) {
        this.textItem = textItem;
        this.colorBackground = colorBackground;
        this.colorPokeball = colorPokeball;
        this.colorPokeballDetails = colorPokeballDetails;
        this.activityClass = activityClass;
    }

    public Class<?> getActivityClass() {
        return activityClass;
    }

    public String getTextItem() {
        return textItem;
    }

    public void setTextItem(String textItem) {
        this.textItem = textItem;
    }

    public int getColorBackground() {
        return colorBackground;
    }

    public void setColorBackground(int colorBackground) {
        this.colorBackground = colorBackground;
    }

    public int getColorPokeball() {
        return colorPokeball;
    }

    public void setColorPokeball(int colorPokeball) {
        this.colorPokeball = colorPokeball;
    }

    public int getColorPokeballDetails() {
        return colorPokeballDetails;
    }

    public void setColorPokeballDetails(int colorPokeballDetails) {
        this.colorPokeballDetails = colorPokeballDetails;
    }
}
