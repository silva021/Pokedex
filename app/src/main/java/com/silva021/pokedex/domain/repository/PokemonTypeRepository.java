package com.silva021.pokedex.domain.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.silva021.pokedex.data.api.MyPokemonApiService;
import com.silva021.pokedex.data.api.ServiceGenerator;
import com.silva021.pokedex.domain.model.pokemon.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonTypeRepository {

    public LiveData<List<Pokemon>> getPokemonByType(int page, String type) {
        final MutableLiveData<List<Pokemon>> data = new MutableLiveData<>();
        ServiceGenerator.createService(MyPokemonApiService.class).getAllPokemonByType(type, page).enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
