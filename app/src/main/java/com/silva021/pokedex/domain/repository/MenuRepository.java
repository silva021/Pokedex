package com.silva021.pokedex.domain.repository;

import android.graphics.Color;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.silva021.pokedex.domain.model.menu.Menu;
import com.silva021.pokedex.presenter.ui.SplashScreenActivity;
import com.silva021.pokedex.presenter.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {
    public LiveData<List<Menu>> getListMenu() {
        MutableLiveData<List<Menu>> listMutableLiveData = new MutableLiveData<>();

        List<Menu> menus = new ArrayList<>();

        menus.add(new Menu("Pokedex", Color.rgb(72, 208, 176), Color.rgb(87, 219, 192), Color.rgb(87, 219, 192), MainActivity.class));
        menus.add(new Menu("Generation", Color.rgb(124, 83, 140), Color.rgb(149, 105, 165), Color.rgb(149, 105, 165), SplashScreenActivity.class));

        listMutableLiveData.setValue(menus);

        return listMutableLiveData;
    }
}
