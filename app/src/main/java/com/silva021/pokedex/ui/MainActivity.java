package com.silva021.pokedex.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.silva021.pokedex.R;
import com.silva021.pokedex.adapter.PokemonAdapter;
import com.silva021.pokedex.api.PokemonService;
import com.silva021.pokedex.api.ServiceGenerator;
import com.silva021.pokedex.listener.RecyclerViewOnClickListener;
import com.silva021.pokedex.model.Pokemon;
import com.silva021.pokedex.model.Abilities;
import com.silva021.pokedex.model.Type;
import com.silva021.pokedex.model.Types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerViewOnClickListener {
    private PokemonAdapter pokemonAdapter;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecycler();
    }

    private void initRecycler() {
        pokemonAdapter = new PokemonAdapter(getApplicationContext(), returnList());
        recyclerView.setAdapter(pokemonAdapter);
        pokemonAdapter.setPokemonListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

    private List<Pokemon> returnList() {
        ServiceGenerator.createService(PokemonService.class).getAllPokemon(1).enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon("007", "Squirtle", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png", "Water", null, 1, 0, new Abilities(44, 48, 65, 50, 64, 43)));
        pokemons.add(new Pokemon("390", "Chimchar", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/390.png", "Water", null, 1, 0, new Abilities(44, 48, 65, 50, 64, 43)));
        pokemons.add(new Pokemon("252", "Treecko",  "https://assets.pokemon.com/assets/cms2/img/pokedex/full/252.png", "Water", null, 1, 0, new Abilities(44, 48, 65, 50, 64, 43)));
        return pokemons;
    }


    @Override
    public void onItemClick(@NonNull View view, @NonNull Pokemon pokemon) {
        Intent intent = new Intent(this, PokemonActivity.class);
        intent.putExtra("object", pokemon);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}