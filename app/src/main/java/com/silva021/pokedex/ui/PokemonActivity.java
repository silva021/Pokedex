package com.silva021.pokedex.ui;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.google.android.material.tabs.TabLayout;
import com.silva021.pokedex.AboutPokemonFragment;
import com.silva021.pokedex.BaseStatsPokemonFragment;
import com.silva021.pokedex.R;
import com.silva021.pokedex.adapter.PokemonTypeAdapter;
import com.silva021.pokedex.adapter.ViewPagerPokemonAdapter;
import com.silva021.pokedex.model.Pokemon;
import com.silva021.pokedex.model.Type;
import com.silva021.pokedex.utils.MyColor;
import com.silva021.pokedex.utils.PaletteListener;

import java.util.List;


public class PokemonActivity extends AppCompatActivity {
    ImageView imgPokemon, imgLeftArrow, imgPokeball, imgPokeball2;
    TextView txtName, txtCode;
    Pokemon pokemon;
    CoordinatorLayout layout;
    RecyclerView recyclerViewPokemonType;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Palette.PaletteAsyncListener mPaletteListener;

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
        layout = findViewById(R.id.layout);
        imgPokeball = findViewById(R.id.imgPokeball);
        imgPokeball2 = findViewById(R.id.imgPokeball2);
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
                            initRecyclerViewPokemonType(pokemon.getTypes().getList(), myColor[0].colorType());
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