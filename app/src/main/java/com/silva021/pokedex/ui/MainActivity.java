package com.silva021.pokedex.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.silva021.pokedex.R;
import com.silva021.pokedex.adapter.GridPokemonAdapter;
import com.silva021.pokedex.listener.RecyclerViewOnClickListener;
import com.silva021.pokedex.model.Abilities;
import com.silva021.pokedex.model.Ability;
import com.silva021.pokedex.model.Pokemon;
import com.silva021.pokedex.model.Stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewOnClickListener {
    private GridPokemonAdapter gridPokemonAdapter;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        initRecycler();
    }

    private void initRecycler() {
        gridPokemonAdapter = new GridPokemonAdapter(getApplicationContext(), returnList());
        recyclerView.setAdapter(gridPokemonAdapter);
        gridPokemonAdapter.setPokemonListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

    private List<Pokemon> returnList() {
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon("Bulbasaur", "#001", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png", new Stats(45, 49, 49, 65, 65, 45), new Abilities(Arrays.asList(new Ability("Overgrow"), new Ability("Chlorophyll"))),null));
        pokemons.add(new Pokemon("Ivysaur", "#002", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/002.png", new Stats(60, 62, 63, 80, 80, 60), new Abilities(Arrays.asList(new Ability("Overgrow"), new Ability("Chlorophyll"))),null));
//        pokemons.add(new Pokemon("Venusaur", "#003", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/003.png", new Stats(80, 82, 83, 100, 100, 80)));
//        pokemons.add(new Pokemon("Charmeleon", "#002", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/005.png", new Stats(58, 64, 58, 80, 65, 80)));
//        pokemons.add(new Pokemon("Squirtle", "#003", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png", new Stats(44, 48, 65, 50, 64, 43)));
        pokemons.add(new Pokemon("Pikachu", "#025", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png", new Stats(35, 55, 40, 50, 50, 90), new Abilities(Arrays.asList(new Ability("Static"), new Ability("Lightning-rod"))),null));
        pokemons.add(new Pokemon("Lucario", "#448", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/448.png", new Stats(70, 110, 70, 115, 70, 90), new Abilities(Arrays.asList(new Ability("Steadfast"),new Ability("Justified"), new Ability("Inner-focus"))), null));
        return pokemons;
    }


    @Override
    public void onItemClick(@NonNull View view, @NonNull Pokemon pokemon) {
        Intent intent = new Intent(this, PokemonActivity.class);
        intent.putExtra("object", pokemon);
        startActivity(intent);
    }

}