package com.silva021.pokedex.domain.repository;

import android.graphics.Color;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.silva021.pokedex.R;
import com.silva021.pokedex.domain.model.menu.Menu;
import com.silva021.pokedex.domain.model.menu.MenuGeneration;
import com.silva021.pokedex.presenter.ui.main.MainActivity;
import com.silva021.pokedex.presenter.ui.pokemons_generation.menu.MenuPokemonGenerationActivity;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {
    public LiveData<List<Menu>> getListMenu() {
        MutableLiveData<List<Menu>> listMutableLiveData = new MutableLiveData<>();

        List<Menu> menus = new ArrayList<>();

        menus.add(new Menu("Pokedex", Color.rgb(72, 208, 176), Color.rgb(87, 219, 192), Color.rgb(87, 219, 192), MainActivity.class));
        menus.add(new Menu("Generation", Color.rgb(124, 83, 140), Color.rgb(149, 105, 165), Color.rgb(149, 105, 165), MenuPokemonGenerationActivity.class));

        listMutableLiveData.setValue(menus);

        return listMutableLiveData;
    }
    public LiveData<List<MenuGeneration>> getListMenuGeneration() {
        MutableLiveData<List<MenuGeneration>> listMutableLiveData = new MutableLiveData<>();

        List<MenuGeneration> menus = new ArrayList<>();

        menus.add(new MenuGeneration(R.drawable.gen1, "Generation 1 ", 1));
        menus.add(new MenuGeneration(R.drawable.gen2, "Generation 2", 2));
        menus.add(new MenuGeneration(R.drawable.gen3, "Generation 3", 3));
        menus.add(new MenuGeneration(R.drawable.gen4, "Generation 4", 4));
        menus.add(new MenuGeneration(R.drawable.gen5, "Generation 5", 5));
        menus.add(new MenuGeneration(R.drawable.gen6, "Generation 6", 6));;


        listMutableLiveData.setValue(menus);

        return listMutableLiveData;
    }
}
