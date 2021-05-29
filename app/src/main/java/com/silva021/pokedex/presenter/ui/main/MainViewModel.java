package com.silva021.pokedex.presenter.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.silva021.pokedex.domain.model.pokemon.Pokemon;
import com.silva021.pokedex.domain.repository.MainRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MainRepository mainRepository = new MainRepository();
    private MutableLiveData<List<Pokemon>> liveDataAllPokemon;

    public LiveData<List<Pokemon>> getAllPokemon(int page) {
        return mainRepository.getAllPokemon(page);
    }
}
