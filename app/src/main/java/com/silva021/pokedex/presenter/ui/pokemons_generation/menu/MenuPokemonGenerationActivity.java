package com.silva021.pokedex.presenter.ui.pokemons_generation.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.silva021.pokedex.R;
import com.silva021.pokedex.domain.model.menu.MenuGeneration;
import com.silva021.pokedex.presenter.adapter.GenerationAdapter;
import com.silva021.pokedex.presenter.ui.pokemons_generation.list.PokemonGenerationActivity;
import com.silva021.pokedex.presenter.ui.pokemons_type.PokemonTypeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuPokemonGenerationActivity extends AppCompatActivity implements GenerationAdapter.OnClickListener {
    MenuGenerationViewModel mViewModel;
    private GenerationAdapter mAdapter;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    private List<MenuGeneration> menuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pokemon_generation);
        ButterKnife.bind(this);
        mViewModel = new ViewModelProvider(this).get(MenuGenerationViewModel.class);
        initRecycler();
    }

    private void initRecycler() {
        mAdapter = new GenerationAdapter(menuList);
        mRecyclerView.setAdapter(mAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter.setListener(this);
        mViewModel.menuRepository.getListMenuGeneration().observe(this, menus -> {
            mAdapter.setData(menus);
        });
    }


    @Override
    public void onClickListener(MenuGeneration menu) {
        Intent intent = new Intent(this, PokemonGenerationActivity.class);
        intent.putExtra("generation", menu.getGeneration());
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}