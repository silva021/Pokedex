package com.silva021.pokedex.presenter.adapter;

import android.content.Context;
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
import com.silva021.pokedex.domain.model.Abilities;
import com.silva021.pokedex.presenter.adapter.base.BaseViewHolder;
import com.silva021.pokedex.presenter.adapter.listener.RecyclerViewOnClickListener;
import com.silva021.pokedex.domain.model.Pokemon;
import com.silva021.pokedex.domain.model.Type;
import com.silva021.pokedex.domain.utils.MyColorPokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PokemonAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;
    private List<Pokemon> mPokemons;
    private Context mContext;
    private RecyclerViewOnClickListener mPokemonListener;

    public void setPokemonListener(RecyclerViewOnClickListener mPokemonListener) {
        this.mPokemonListener = mPokemonListener;
    }

    public PokemonAdapter(Context mContext, List<Pokemon> mPokemons) {
        this.mPokemons = mPokemons;
        this.mContext = mContext;
    }

    public void addPokemons(List<Pokemon> data) {
        if (data != null) {
            mPokemons.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = mPokemons.size() - 1;
        if (position != -1) {
            Pokemon item = getItem(position);
            if (item != null) {
                mPokemons.remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    public void addLoading() {
        isLoaderVisible = true;
        mPokemons.add(new Pokemon("", "", "", "", "", 0, 1, new Abilities(0, 0, 0, 0, 0, 0)));
        notifyItemInserted(mPokemons.size() - 1);
    }

    public void clear() {
        mPokemons.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == mPokemons.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pokemon_pokedex, parent, false));
            case VIEW_TYPE_LOADING:
                return new ProgressHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_loading, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    private void initRecycler(RecyclerView recyclerView, Pokemon pokemon, int color) {
        PokemonTypeAdapter pokemonAdapter = new PokemonTypeAdapter(returnTypeList(Arrays.asList(new Type(pokemon.getType1()), new Type(pokemon.getType2()))), mContext, color);
        recyclerView.setAdapter(pokemonAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private List<Type> returnTypeList(List<Type> types) {
        List<Type> typeList = new ArrayList<>();
        for (Type type : types) {
            if (type.getName() != null)
                typeList.add(type);
        }
        return typeList;
    }

    @Override
    public int getItemCount() {
        return mPokemons.size();
    }

    private Pokemon getItem(int position) {
        return mPokemons.get(position);
    }


    public class ViewHolder extends BaseViewHolder {
        @BindView(R.id.txtCode)
        TextView txtCode;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.cardViewLayout)
        CardView cardView;
        @BindView(R.id.imgPokemon)
        ImageView imgPokemon;
        @BindView(R.id.imgPokeball)
        ImageView imgPokeball;
        @BindView(R.id.recycler)
        RecyclerView recycler;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView.setOnClickListener(this::onClick);

        }

        public void onBind(int position) {
            super.onBind(position);
            Pokemon pokemon = mPokemons.get(position);
            txtName.setText(pokemon.getName());
            txtCode.setText("#" + pokemon.getId());
            cardView.setCardBackgroundColor(MyColorPokemon.colorCardView(pokemon.getType1()));
            imgPokeball.setColorFilter(MyColorPokemon.colorPokeball(pokemon.getType1()));
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
                                txtCode.setTextColor(palette1.getMutedSwatch().getTitleTextColor());
                                initRecycler(recycler, pokemon, MyColorPokemon.colorType(pokemon.getType1()));
                            });
                            return false;
                        }
                    })
                    .into(imgPokemon);
        }

        @Override
        protected void clear() {

        }

        private void onClick(View view) {
            Pokemon pokemon = getItem(getAdapterPosition());
            if (mPokemonListener != null)
                mPokemonListener.onItemClick(view, pokemon);

        }
    }

    public class ProgressHolder extends BaseViewHolder {
        ProgressHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {
        }
    }
}
