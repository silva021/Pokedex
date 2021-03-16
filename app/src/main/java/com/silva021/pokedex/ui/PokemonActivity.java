package com.silva021.pokedex.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.silva021.pokedex.AboutPokemonFragment;
import com.silva021.pokedex.BaseStatsPokemonFragment;
import com.silva021.pokedex.R;
import com.silva021.pokedex.adapter.PokemonTypeAdapter;
import com.silva021.pokedex.adapter.ViewPagerPokemonAdapter;
import com.silva021.pokedex.model.Pokemon;
import com.silva021.pokedex.model.Type;

import java.util.List;


public class PokemonActivity extends AppCompatActivity {
    ImageView imgPokemon, imgLeftArrow;
    TextView txtName, txtCode;
    Pokemon pokemon;
    RecyclerView recyclerViewPokemonType;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private AboutPokemonFragment aboutPokemonFragment;
    private BaseStatsPokemonFragment baseStatsPokemonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        imgPokemon = findViewById(R.id.imgPokemon);
        imgLeftArrow = findViewById(R.id.imgLeftArrow);
        txtName = findViewById(R.id.txtName);
        txtCode = findViewById(R.id.txtCode);
        recyclerViewPokemonType = findViewById(R.id.recyclerViewPokemonType);

        if (getIntent() != null) {
            pokemon = (Pokemon) getIntent().getSerializableExtra("object");
            updateView(pokemon);
        }


        imgLeftArrow.setOnClickListener(v ->
                finish()
        );

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        Bundle bundle = new Bundle();
        bundle.putSerializable("object", pokemon);

        aboutPokemonFragment = new AboutPokemonFragment();
        baseStatsPokemonFragment = new BaseStatsPokemonFragment();

        baseStatsPokemonFragment.setArguments(bundle);
        aboutPokemonFragment.setArguments(bundle);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerPokemonAdapter viewPagerAdapter = new ViewPagerPokemonAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(aboutPokemonFragment, "About");
        viewPagerAdapter.addFragment(baseStatsPokemonFragment, "Base Stats");
        viewPager.setAdapter(viewPagerAdapter);

    }

    private void updateView(Pokemon pokemon) {
        Glide.with(getApplicationContext())
                .load(pokemon.getUrlImage())
                .placeholder(R.drawable.loading)
                .into(imgPokemon);

        txtName.setText(pokemon.getName());
        txtCode.setText(pokemon.getId());

        initRecyclerViewPokemonType(pokemon.getTypes().getList());
    }

    private void initRecyclerViewPokemonType(List<Type> list) {
        PokemonTypeAdapter pokemonTypeAdapter = new PokemonTypeAdapter(list, getApplicationContext(), true);
        recyclerViewPokemonType.setAdapter(pokemonTypeAdapter);
    }

}