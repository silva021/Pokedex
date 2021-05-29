package com.silva021.pokedex.domain.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.silva021.pokedex.data.api.MyPokemonApiService;
import com.silva021.pokedex.data.api.ServiceGenerator;
import com.silva021.pokedex.domain.model.pokemon.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    public LiveData<List<Pokemon>> getAllPokemon(int page) {
        final MutableLiveData<List<Pokemon>> data = new MutableLiveData<>();
        ServiceGenerator.createService(MyPokemonApiService.class).getAllPokemon(page).enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                data.setValue(response.body());
                Log.d("MainRepository", "ok");
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                data.setValue(null);
                Log.d("MainRepository", "deu merda em");

            }
        });

        return data;
    }
}
