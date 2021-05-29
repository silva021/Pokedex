package com.silva021.pokedex.presenter.ui.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.silva021.pokedex.domain.model.menu.Menu;
import com.silva021.pokedex.domain.repository.MenuRepository;

import java.util.List;

public class MenuViewModel extends ViewModel {
    MenuRepository menuRepository = new MenuRepository();

    public LiveData<List<Menu>> getMenuList() {
        return menuRepository.getListMenu();
    }
}
