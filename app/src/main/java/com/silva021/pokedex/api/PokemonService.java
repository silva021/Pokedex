package com.silva021.pokedex.api;

import com.silva021.pokedex.model.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonService {

    @GET("/")
    Call<List<Pokemon>> getAllPokemon(@Query(value = "page") int page);
}
