package com.silva021.pokedex.ui;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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
import com.silva021.pokedex.ui.fragment.AboutPokemonFragment;
import com.silva021.pokedex.ui.fragment.BaseStatsPokemonFragment;
import com.silva021.pokedex.R;
import com.silva021.pokedex.adapter.PokemonTypeAdapter;
import com.silva021.pokedex.adapter.ViewPagerPokemonAdapter;
import com.silva021.pokedex.model.Pokemon;
import com.silva021.pokedex.model.Type;
import com.silva021.pokedex.utils.MyColor;
import com.silva021.pokedex.utils.PaletteListener;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PokemonActivity extends AppCompatActivity {
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

    Pokemon pokemon;
    private Palette.PaletteAsyncListener mPaletteListener;

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
        final MyColor[] myColor = new MyColor[1];
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
                        mPaletteListener = new PaletteListener();
                        Palette.from(((BitmapDrawable) resource).getBitmap()).generate(palette1 -> {
                            assert palette1 != null;
                            myColor[0] = new MyColor(palette1.getMutedColor(Color.YELLOW));
                            imgPokeball.setColorFilter(myColor[0].colorPokeball());
                            imgPokeball2.setColorFilter(myColor[0].colorPokeball());
                            layout.getBackground().setTint(myColor[0].colorCardView());
                            initRecyclerViewPokemonType(Arrays.asList(new Type(pokemon.getType1()), new Type(pokemon.getType2())), myColor[0].colorType());
                        });
                        return false;
                    }
                })
                .into(imgPokemon);

        txtName.setText(pokemon.getName());
        txtCode.setText(pokemon.getId());

    }

    private void initRecyclerViewPokemonType(List<Type> list, int color) {
        PokemonTypeAdapter pokemonTypeAdapter = new PokemonTypeAdapter(list, getApplicationContext(), color, true);
        recyclerViewPokemonType.setAdapter(pokemonTypeAdapter);
    }

}