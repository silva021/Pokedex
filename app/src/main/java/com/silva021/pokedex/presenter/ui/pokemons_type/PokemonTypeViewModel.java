package com.silva021.pokedex.presenter.ui.pokemons_type;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.silva021.pokedex.domain.model.pokemon.Pokemon;
import com.silva021.pokedex.domain.repository.PokemonRepository;

import java.util.List;

public class PokemonTypeViewModel extends ViewModel {
    PokemonRepository repository = new PokemonRepository();

    public LiveData<List<Pokemon>> getPokemonByType(int page, String type) {
        return repository.getPokemonByType(page, type);
    }

}
