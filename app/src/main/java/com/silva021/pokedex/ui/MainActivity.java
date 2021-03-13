package com.silva021.pokedex.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.silva021.pokedex.R;
import com.silva021.pokedex.adapter.GridPokemonAdapter;
import com.silva021.pokedex.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridPokemonAdapter gridPokemonAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        initRecycler();
    }

    private void initRecycler() {
        gridPokemonAdapter = new GridPokemonAdapter(getApplicationContext(), returnList());
        recyclerView.setAdapter(gridPokemonAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private List<Pokemon> returnList() {
        List<Pokemon> pokemons = new ArrayList<>();
//        pokemons.add(new Pokemon("Bulbasaur", "#001", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png"));
//        pokemons.add(new Pokemon("Ivysaur", "#002", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/002.png"));
//        pokemons.add(new Pokemon("Venusaur", "#003", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/003.png"));
//        pokemons.add(new Pokemon("Lucario", "#443", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/448.png"));

        pokemons.add(new Pokemon("Bulbasaur", "#001", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png"));
        pokemons.add(new Pokemon("Charmeleon", "#002", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/005.png"));
        pokemons.add(new Pokemon("Squirtle", "#003", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png"));
        pokemons.add(new Pokemon("Pikachu", "#025", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png"));
        pokemons.add(new Pokemon("Lucario", "#448", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/448.png"));
       return pokemons;
    }
}