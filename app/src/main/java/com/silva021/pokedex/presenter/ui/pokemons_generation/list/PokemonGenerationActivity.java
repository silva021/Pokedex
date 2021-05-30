package com.silva021.pokedex.presenter.ui.pokemons_generation.list;

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
import com.silva021.pokedex.presenter.adapter.PokemonAdapter;
import com.silva021.pokedex.presenter.adapter.listener.PaginationListener;
import com.silva021.pokedex.presenter.adapter.listener.RecyclerViewOnClickListener;
import com.silva021.pokedex.presenter.ui.pokemon.PokemonActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.silva021.pokedex.presenter.adapter.listener.PaginationListener.PAGE_START;

public class PokemonGenerationActivity extends AppCompatActivity implements RecyclerViewOnClickListener,
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
    PokemonGenerationViewModel mViewModel;
    List<Pokemon> mPokemonList = new ArrayList<>();

    private boolean isLastPage = false;
    private int totalPage = 100;
    private boolean isLoading = false;
    private int currentPage = 1;

    private int generation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_generation);
        ButterKnife.bind(this);
        mViewModel = new ViewModelProvider(this).get(PokemonGenerationViewModel.class);

        if (getIntent() != null) {
            generation = getIntent().getIntExtra("generation", 1);
        }

        initRecycler();
    }


    private void initRecycler() {
        mPokemonAdapter = new PokemonAdapter(getApplicationContext(), mPokemonList);
        mSwipeRefresh.setOnRefreshListener(this);
        mRecyclerView.setAdapter(mPokemonAdapter);
        mPokemonAdapter.setPokemonListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        getPokemonList(PAGE_START);
        mRecyclerView.addOnScrollListener(new PaginationListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                getPokemonList(currentPage);
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

    private void getPokemonList(int page) {
        mViewModel.getPokemonGenerationList(page, generation).observe(this, pokemons -> {

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
        getPokemonList(currentPage);
    }
}