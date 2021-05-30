package com.silva021.pokedex.presenter.ui.pokemons_generation.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.silva021.pokedex.domain.model.menu.MenuGeneration;
import com.silva021.pokedex.domain.repository.MenuRepository;

import java.util.List;

public class MenuGenerationViewModel extends ViewModel {
    MenuRepository menuRepository = new MenuRepository();

    public LiveData<List<MenuGeneration>> getMenuGenerationList() {
        return menuRepository.getListMenuGeneration();
    }
}
