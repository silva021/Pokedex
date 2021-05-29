package com.silva021.pokedex.presenter.ui.pokemons_type;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silva021.pokedex.R;
import com.silva021.pokedex.domain.model.pokemon.Pokemon;
import com.silva021.pokedex.domain.utils.MyColorPokemon;
import com.silva021.pokedex.presenter.adapter.PokemonAdapter;
import com.silva021.pokedex.presenter.adapter.listener.PaginationListener;
import com.silva021.pokedex.presenter.adapter.listener.RecyclerViewOnClickListener;
import com.silva021.pokedex.presenter.ui.pokemon.PokemonActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.silva021.pokedex.presenter.adapter.listener.PaginationListener.PAGE_START;

public class PokemonTypeActivity extends AppCompatActivity implements RecyclerViewOnClickListener,
        SwipeRefreshLayout.OnRefreshListener {
    private PokemonAdapter mPokemonAdapter;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.txtTypePokemon)
    TextView txtTypePokemon;
    @BindView(R.id.imgPokeball)
    ImageView imgPokeball;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;
    PokemonTypeViewModel mViewModel;
    List<Pokemon> mPokemonList = new ArrayList<>();

    private boolean isLastPage = false;
    private int totalPage = 100;
    private boolean isLoading = false;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_type);
        ButterKnife.bind(this);
        mViewModel = new ViewModelProvider(this).get(PokemonTypeViewModel.class);

        if (getIntent() != null) {
            String pokemonType = getIntent().getStringExtra("type");
            updateView(pokemonType);
        }

        initRecycler();
    }

    private void updateView(String pokemonType) {
        txtTypePokemon.setText(pokemonType);
        txtTypePokemon.setTextColor(MyColorPokemon.colorType(pokemonType));
        imgPokeball.setColorFilter(MyColorPokemon.colorPokeball(pokemonType));
    }

    private void initRecycler() {
        mPokemonAdapter = new PokemonAdapter(getApplicationContext(), mPokemonList);
        mSwipeRefresh.setOnRefreshListener(this);
        mRecyclerView.setAdapter(mPokemonAdapter);
        mPokemonAdapter.setPokemonListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        getPokemonByTypeList(PAGE_START);
        mRecyclerView.addOnScrollListener(new PaginationListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                getPokemonByTypeList(currentPage);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

    }

    private void getPokemonByTypeList(int page) {
        mViewModel.getPokemonByType(page, txtTypePokemon.getText().toString()).observe(this, pokemons -> {

            if (currentPage != PAGE_START)
                mPokemonAdapter.removeLoading();
            mPokemonAdapter.addPokemons(pokemons);
            mSwipeRefresh.setRefreshing(false);

            if (currentPage < totalPage) {
                mPokemonAdapter.addLoading();
            } else {
                isLastPage = true;
            }
            isLoading = false;
        });
    }


    @Override
    public void onItemClick(@NonNull View view, @NonNull Pokemon pokemon) {
        Intent intent = new Intent(this, PokemonActivity.class);
        intent.putExtra("object", pokemon);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onRefresh() {
        currentPage = PAGE_START;
        isLastPage = false;
        mPokemonAdapter.clear();
        getPokemonByTypeList(currentPage);
    }
}