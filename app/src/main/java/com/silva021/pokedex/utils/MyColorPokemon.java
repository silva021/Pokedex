package com.silva021.pokedex.utils;

import android.graphics.Color;


public class MyColorPokemon {
    private static int color;
    private static int blue;
    private static int red;
    private static int green;

    public MyColorPokemon(int color) {
        blue = Color.blue(color);
        red = Color.red(color);
        green = Color.green(color);
        this.color = color;
    }


    public static int colorCardView(String type) {
        switch (type) {
            case "Fire":
            case "Fighting":
            case "Rock":
                return Color.rgb(251, 108, 108);
            case "Water":
            case "Ice":
            case "Dragon":
                return Color.rgb(118, 189, 254);
            case "Electric":
            case "Psychic":
                return Color.rgb(255, 206, 75);
            case "Ground":
            case "Fairy":
            case "Normal":
            case "Flying":
            case "Steel":
                return Color.rgb(177, 115, 108);
            case "Poison":
            case "Ghost":
            case "Dark":
                return Color.rgb(124, 83, 140);
            case "Grass":
            case "Bug":
                return Color.rgb(72, 208, 176);
            default:
                return Color.rgb(0, 0, 0);
        }
    }


    public static int colorPokeball(String type) {
        switch (type) {
            case "Fire":
            case "Fighting":
            case "Rock":
                return Color.rgb(252, 127, 127);
            case "Water":
            case "Ice":
            case "Dragon":
                return Color.rgb(134, 202, 254);
            case "Electric":
            case "Psychic":
                return Color.rgb(255, 218, 91);
            case "Ground":
            case "Fairy":
            case "Normal":
            case "Flying":
            case "Steel":
                return Color.rgb(193, 134, 127);
            case "Poison":
            case "Dark":
            case "Ghost":
                return Color.rgb(149, 105, 165);
            case "Grass":
            case "Bug":
                return Color.rgb(87, 219, 192);
            default:
                return Color.rgb(0, 0, 0);
        }
    }

    public static int colorType(String type) {
        switch (type) {
            case "Fire":
            case "Fighting":
            case "Rock":
                return Color.rgb(253, 133, 133);
            case "Water":
            case "Ice":
            case "Dragon":
                return Color.rgb(143, 209, 254);
            case "Electric":
            case "Psychic":
                return Color.rgb(255, 227, 122);
            case "Ground":
            case "Fairy":
            case "Normal":
            case "Flying":
            case "Steel":
                return Color.rgb(187, 125, 118);
            case "Poison":
            case "Ghost":
            case "Dark":
                return Color.rgb(135, 91, 152);
            case "Grass":
            case "Bug":
                return Color.rgb(93, 223, 192);
            default:
                return Color.rgb(0, 0, 0);
        }
    }
}
