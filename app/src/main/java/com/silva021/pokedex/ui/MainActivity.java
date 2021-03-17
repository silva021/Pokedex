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
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon("Squirtle", "#007", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png", new Types(Collections.singletonList(new Type("Water"))), new Stats(44, 48, 65, 50, 64, 43), new Abilities(Arrays.asList(new Ability("Torrent"), new Ability("Rain-Dish"))), new EggGroups(Arrays.asList(new EggGroup("Monster"), new EggGroup("Water")))));
        pokemons.add(new Pokemon("Chimchar", "#390", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/390.png", new Types(Collections.singletonList(new Type("Fire"))), new Stats(44, 58, 44, 58, 44, 61), new Abilities(Arrays.asList(new Ability("Blaze"), new Ability("Iron-Fist"))), new EggGroups(Arrays.asList(new EggGroup("Ground"), new EggGroup("Humanshape")))));
        pokemons.add(new Pokemon("Treecko", "#252", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/252.png", new Types(Collections.singletonList(new Type("Grass"))), new Stats(40, 45, 35, 65, 55, 70), new Abilities(Arrays.asList(new Ability("Overgrow"), new Ability("Unburden"))), new EggGroups(Arrays.asList(new EggGroup("Monster"), new EggGroup("Plant")))));
        return pokemons;
    }


    @Override
    public void onItemClick(@NonNull View view, @NonNull Pokemon pokemon) {
        Intent intent = new Intent(this, PokemonActivity.class);
        intent.putExtra("object", pokemon);
        startActivity(intent);
    }

}