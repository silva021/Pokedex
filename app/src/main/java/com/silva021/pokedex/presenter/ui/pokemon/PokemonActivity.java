package com.silva021.pokedex.presenter.ui.pokemon;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.silva021.pokedex.presenter.ui.pokemon.fragment.AboutPokemonFragment;
import com.silva021.pokedex.presenter.ui.pokemon.fragment.BaseStatsPokemonFragment;
import com.silva021.pokedex.R;
import com.silva021.pokedex.presenter.adapter.PokemonTypeAdapter;
import com.silva021.pokedex.presenter.adapter.ViewPagerPokemonAdapter;
import com.silva021.pokedex.domain.model.Pokemon;
import com.silva021.pokedex.domain.model.Type;
import com.silva021.pokedex.domain.utils.MyColorPokemon;
import com.silva021.pokedex.presenter.ui.pokemons_type.PokemonTypeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PokemonActivity extends AppCompatActivity implements PokemonTypeAdapter.RecyclerViewClickListener {
    @BindView(R.id.imgPokemon)
    ImageView imgPokemon;
    @BindView(R.id.imgLeftArrow)
    ImageView imgLeftArrow;
    @BindView(R.id.imgPokeball)
    ImageView imgPokeball;
    @BindView(R.id.imgPokeball2)
    ImageView imgPokeball2;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtCode)
    TextView txtCode;
    @BindView(R.id.layout)
    CoordinatorLayout layout;
    @BindView(R.id.recyclerViewPokemonType)
    RecyclerView recyclerViewPokemonType;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.btnLike)
    LikeButton btnLike;

    private Pokemon pokemon;

    private AboutPokemonFragment aboutPokemonFragment;
    private BaseStatsPokemonFragment baseStatsPokemonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            pokemon = (Pokemon) getIntent().getSerializableExtra("object");
            updateView(pokemon);
        }

        imgLeftArrow.setOnClickListener(v -> {
                    finish();
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
        );

        btnLike.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                showSnackBar("I liked this Pokémon");
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                showSnackBar("I didn't like this pokémon");
            }
        });

        initViewPager();

    }

    private void initViewPager() {
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

    private void showSnackBar(String text) {
        Snackbar.make(getCurrentFocus(), text, Snackbar.LENGTH_SHORT).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
    }

    private void updateView(Pokemon pokemon) {
        Glide.with(getApplicationContext())
                .load(pokemon.getUrlImage())
                .placeholder(R.drawable.loading)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(getApplicationContext(), "falhou", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        initRecyclerViewPokemonType(pokemon, MyColorPokemon.colorType(pokemon.getType1()));
                        return false;
                    }
                })
                .into(imgPokemon);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layout.getBackground().setTint(MyColorPokemon.colorCardView(pokemon.getType1()));
        }
        imgPokeball.setColorFilter(MyColorPokemon.colorPokeball(pokemon.getType1()));
        imgPokeball2.setColorFilter(MyColorPokemon.colorPokeball(pokemon.getType1()));
        txtName.setText(pokemon.getName());
        txtCode.setText("#" + pokemon.getId());

    }

    private void initRecyclerViewPokemonType(Pokemon pokemon, int color) {
        PokemonTypeAdapter pokemonTypeAdapter = new PokemonTypeAdapter(returnListType(pokemon), getApplicationContext(), color, true);
        recyclerViewPokemonType.setAdapter(pokemonTypeAdapter);
        pokemonTypeAdapter.setRecyclerViewClickListener(this);
    }

    List<Type> returnListType(Pokemon pokemon) {
        List<Type> types = new ArrayList<>();
        types.add(new Type(pokemon.getType1()));
        if (pokemon.getType2() != null) {
            types.add(new Type(pokemon.getType2()));
        }
        return types;
    }

    @Override
    public void onClickListener(String pokemonType) {
        Intent intent = new Intent(this, PokemonTypeActivity.class);
        intent.putExtra("type", pokemonType);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}