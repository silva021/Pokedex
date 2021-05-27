package com.silva021.pokedex.domain.utils;


import androidx.palette.graphics.Palette;

import androidx.annotation.Nullable;

public class PaletteListener implements Palette.PaletteAsyncListener {
    @Override
    public void onGenerated(@Nullable Palette palette) {
        int vibrant = palette.getVibrantColor(0x000000);
        int vibrantLight = palette.getLightVibrantColor(0x000000);
        int vibrantDark = palette.getDarkVibrantColor(0x000000);
        int muted = palette.getMutedColor(0x000000);
        int mutedLight = palette.getLightMutedColor(0x000000);
        int mutedDark = palette.getDarkMutedColor(0x000000);
    }
}
