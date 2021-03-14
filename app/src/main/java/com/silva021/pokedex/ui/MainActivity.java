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
import com.silva021.pokedex.adapter.PokemonAdapter;
import com.silva021.pokedex.listener.RecyclerViewOnClickListener;
import com.silva021.pokedex.model.Abilities;
import com.silva021.pokedex.model.Ability;
import com.silva021.pokedex.model.EggGroup;
import com.silva021.pokedex.model.EggGroups;
import com.silva021.pokedex.model.Pokemon;
import com.silva021.pokedex.model.Stats;
import com.silva021.pokedex.model.Type;
import com.silva021.pokedex.model.Types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewOnClickListener {
    private PokemonAdapter pokemonAdapter;
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
        pokemonAdapter = new PokemonAdapter(getApplicationContext(), returnList());
        recyclerView.setAdapter(pokemonAdapter);
        pokemonAdapter.setPokemonListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

    private List<Pokemon> returnList() {
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon("Bulbasaur", "#001", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png", new Types(Arrays.asList(new Type("Grass"), new Type("Poison"))), new Stats(45, 49, 49, 65, 65, 45), new Abilities(Arrays.asList(new Ability("Overgrow"), new Ability("Chlorophyll"))), new EggGroups(Arrays.asList(new EggGroup("Monster"), new EggGroup("Plant")))));
        pokemons.add(new Pokemon("Charmeleon", "#005", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/005.png", new Types(Arrays.asList(new Type("Fire"))), new Stats(58, 64, 58, 80, 65, 80), new Abilities(Arrays.asList(new Ability("Blaze"), new Ability("Solar-Power"))), new EggGroups(Arrays.asList(new EggGroup("Monster"), new EggGroup("Dragon")))));
        pokemons.add(new Pokemon("Squirtle", "#007", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png", new Types(Arrays.asList(new Type("Water"))), new Stats(44, 48, 65, 50, 64, 43), new Abilities(Arrays.asList(new Ability("Torrent"), new Ability("Rain-Dish"))), new EggGroups(Arrays.asList(new EggGroup("Monster"), new EggGroup("Water")))));
        pokemons.add(new Pokemon("Pikachu", "#025", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png", new Types(Arrays.asList(new Type("Electric"))), new Stats(35, 55, 40, 50, 50, 90), new Abilities(Arrays.asList(new Ability("Static"), new Ability("Lightning-Rod"))), new EggGroups(Arrays.asList(new EggGroup("Ground"), new EggGroup("Fairy")))));
        pokemons.add(new Pokemon("Lucario", "#448", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/448.png", new Types(Arrays.asList(new Type("Fighting"), new Type("Steel"))), new Stats(70, 110, 70, 115, 70, 90), new Abilities(Arrays.asList(new Ability("Steadfast"), new Ability("Justified"), new Ability("Inner-focus"))), new EggGroups(Arrays.asList(new EggGroup("Ground"), new EggGroup("Humanshape")))));
        return pokemons;
    }


    @Override
    public void onItemClick(@NonNull View view, @NonNull Pokemon pokemon) {
        Intent intent = new Intent(this, PokemonActivity.class);
        intent.putExtra("object", pokemon);
        startActivity(intent);
    }

}