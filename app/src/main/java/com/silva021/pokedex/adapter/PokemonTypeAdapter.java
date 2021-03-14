package com.silva021.pokedex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.silva021.pokedex.R;
import com.silva021.pokedex.model.Type;

import java.util.List;

public class PokemonTypeAdapter extends RecyclerView.Adapter<PokemonTypeAdapter.ViewHolder> {
    List<Type> mList;
    Context mContext;

    public PokemonTypeAdapter(List<Type> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_pokemon_type, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonTypeAdapter.ViewHolder holder, int position) {
        Type type = mList.get(position);
        holder.txtType.setText(type.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtType = itemView.findViewById(R.id.txtType);
        }
    }
}
