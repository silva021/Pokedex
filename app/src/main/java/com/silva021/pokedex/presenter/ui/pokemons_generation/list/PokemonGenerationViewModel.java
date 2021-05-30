package com.silva021.pokedex.presenter.ui.pokemons_generation.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.silva021.pokedex.domain.model.pokemon.Pokemon;
import com.silva021.pokedex.domain.repository.PokemonRepository;

import java.util.List;

public class PokemonGenerationViewModel extends ViewModel {
    PokemonRepository pokemonRepository = new PokemonRepository();

    public LiveData<List<Pokemon>> getPokemonGenerationList(int page, int generation) {
        return pokemonRepository.getAllPokemonByGeneration(page, generation);
    }
}
