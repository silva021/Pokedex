package com.silva021.pokedex.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.silva021.pokedex.R;
import com.silva021.pokedex.listener.RecyclerViewOnClickListener;
import com.silva021.pokedex.model.Pokemon;
import com.silva021.pokedex.utils.MyColor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    List<Pokemon> mPokemons;
    Context mContext;
    private RecyclerViewOnClickListener mPokemonListener;

    public void setPokemonListener(RecyclerViewOnClickListener mPokemonListener) {
        this.mPokemonListener = mPokemonListener;
    }

    public PokemonAdapter(Context mContext, List<Pokemon> mPokemons) {
        this.mPokemons = mPokemons;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_pokemon_pokedex, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.ViewHolder holder, int position) {
        Pokemon pokemon = mPokemons.get(position);
        final MyColor[] myColor = new MyColor[1];
        holder.txtName.setText(pokemon.getName());
        holder.txtCode.setText(pokemon.getId());
        Glide.with(mContext).
                load(pokemon.getUrlImage())
                .placeholder(R.drawable.loading)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("Erro", "onLoadFailed - Erro ao carregar a foto");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Palette.from(((BitmapDrawable) resource).getBitmap()).generate(palette1 -> {
                            assert palette1 != null;
                            myColor[0] = new MyColor(palette1.getMutedColor(Color.YELLOW));
                            holder.cardView.setCardBackgroundColor(myColor[0].colorCardView());
                            holder.txtCode.setTextColor(palette1.getMutedSwatch().getTitleTextColor());
                            holder.imgPokeball.setColorFilter(myColor[0].colorPokeball());
                            initRecycler(holder.recycler, pokemon, myColor[0].colorType());
                            holder.cardView.setVisibility(View.VISIBLE);
                        });
                        return false;
                    }
                })
                .into(holder.imgPokemon);
    }


    private void initRecycler(RecyclerView recyclerView, Pokemon pokemon, int color) {
        PokemonTypeAdapter pokemonAdapter = new PokemonTypeAdapter(pokemon.getTypes().getList(), mContext, color);
        recyclerView.setAdapter(pokemonAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public int getItemCount() {
        return mPokemons.size();
    }

    private Pokemon getItem(int position) {
        return mPokemons.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtCode)
        TextView txtCode;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.cardViewLayout)
        CardView cardView;
        @BindView(R.id.imgPokemon)
        ImageView imgPokemon;
        @BindView(R.id.imgPokeball)
        ImageView imgPokeball;;
        @BindView(R.id.recycler)
        RecyclerView recycler;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView.setOnClickListener(this::onClick);

        }

        private void onClick(View view) {
            Pokemon pokemon = getItem(getAdapterPosition());
            if (mPokemonListener != null)
                mPokemonListener.onItemClick(view, pokemon);

        }
    }
}
