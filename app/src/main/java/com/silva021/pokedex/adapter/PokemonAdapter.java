package com.silva021.pokedex.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.silva021.pokedex.utils.PaletteListener;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    List<Pokemon> mPokemons;
    Context mContext;
    private Palette.PaletteAsyncListener mPaletteListener;
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
                        Toast.makeText(mContext, "falhou", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        mPaletteListener = new PaletteListener();
                        Palette.from(((BitmapDrawable) resource).getBitmap()).generate(palette1 -> {
                            assert palette1 != null;
                            myColor[0] = new MyColor(palette1.getMutedColor(Color.YELLOW));
                            holder.cardView.setCardBackgroundColor(myColor[0].colorCardView());
                            holder.txtCode.setTextColor(palette1.getMutedSwatch().getTitleTextColor());
                            holder.imgPokeball.setColorFilter(myColor[0].colorPokeball());
                            initRecycler(holder.recycler, pokemon, myColor[0].colorType());
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
        TextView txtCode;
        TextView txtName;
        CardView cardView;
        ImageView imgPokemon, imgPokeball;
        RecyclerView recycler;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCode = itemView.findViewById(R.id.txtCode);
            txtName = itemView.findViewById(R.id.txtName);
            imgPokemon = itemView.findViewById(R.id.imgPokemon);
            imgPokeball= itemView.findViewById(R.id.imgPokeball);
            cardView = itemView.findViewById(R.id.cardview);
            recycler = itemView.findViewById(R.id.recycler);

            cardView.setOnClickListener(view -> {
                Pokemon pokemon = getItem(getAdapterPosition());
                if (mPokemonListener != null)
                    mPokemonListener.onItemClick(view, pokemon);

            });

        }
    }
}
