package com.silva021.pokedex.utils;

import android.graphics.Color;

public class MyUtlis {


    public static String colorToHex(Integer color) {
        int alpha = Color.alpha(color);
        int blue = Color.blue(color);
        int green = Color.green(color);
        int red = Color.red(color);

        String alphaHex = to00Hex(alpha);
        String blueHex = to00Hex(blue);
        String greenHex = to00Hex(green);
        String redHex = to00Hex(red);
        StringBuilder str = new StringBuilder("#");
        str.append(alphaHex);
        str.append(blueHex);
        str.append(greenHex);
        str.append(redHex);

        return str.toString();
    }

    private static String to00Hex(Integer value) {
        String hex = "00" + Integer.toHexString(value);
        return hex.substring(hex.length() - 2, hex.length());
    }
}
