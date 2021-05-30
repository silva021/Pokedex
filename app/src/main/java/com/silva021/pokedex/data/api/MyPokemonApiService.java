package com.silva021.pokedex.data.api;

import com.silva021.pokedex.domain.model.pokemon.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyPokemonApiService {

    @GET("api/pokemon/")
    Call<List<Pokemon>> getAllPokemon(@Query(value = "page") int page);

    @GET("api/pokemon/generation/{generation}")
    Call<List<Pokemon>> getAllPokemonByGeneration( @Path("generation") int generation, @Query(value = "page") int page);

    @GET("api/pokemon/type/{type}")
    Call<List<Pokemon>> getAllPokemonByType(@Path("type") String type, @Query(value = "page") int page);

}
