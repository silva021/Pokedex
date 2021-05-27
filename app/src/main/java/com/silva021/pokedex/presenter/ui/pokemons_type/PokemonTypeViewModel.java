package com.silva021.pokedex.presenter.ui.pokemons_type;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.silva021.pokedex.domain.model.Pokemon;
import com.silva021.pokedex.domain.repository.PokemonTypeRepository;

import java.util.List;

public class PokemonTypeViewModel extends ViewModel {
    PokemonTypeRepository repository = new PokemonTypeRepository();

    public LiveData<List<Pokemon>> getPokemonByType(int page, String type) {
        return repository.getPokemonByType(page, type);
    }

}
