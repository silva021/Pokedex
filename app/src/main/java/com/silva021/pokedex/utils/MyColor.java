package com.silva021.pokedex.utils;

import android.graphics.Color;

public class MyColor {
    private int color;
    private int blue;
    private int red;
    private int green;

    public MyColor(int color) {
        blue = Color.blue(color);
        red = Color.red(color);
        green = Color.green(color);
        this.color = color;
    }


    public int colorCardView() {
        if (red > blue && red > green) {
            return Color.rgb(251, 108, 108);
        } else if (blue > red && blue > green) {
            return Color.rgb(118, 189, 254);
        } else if (green > red && green > blue) {
            return Color.rgb(72, 208, 176);
        } else {
            return Color.rgb(0, 0, 0);
        }
    }


    public int colorPokeball() {
        if (red > blue && red > green) {
            return Color.rgb(252, 127, 127);
        } else if (blue > red && blue > green) {
            return Color.rgb(134, 202, 254);
        } else if (green > red && green > blue) {
            return Color.rgb(87, 219, 192);
        } else {
            return Color.rgb(0, 0, 0);
        }
    }

    public int colorType() {
        if (red > blue && red > green) {
            return Color.rgb(253, 133, 133);
        } else if (blue > red && blue > green) {
            return Color.rgb(143, 209, 254);
        } else if (green > red && green > blue) {
            return Color.rgb(93, 223, 192);
        } else {
            return Color.rgb(0, 0, 0);
        }
    }

}
