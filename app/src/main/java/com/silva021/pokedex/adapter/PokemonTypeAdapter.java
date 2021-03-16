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
    boolean mDetails = false;

    public PokemonTypeAdapter(List<Type> mList, Context mContext, boolean mDetails) {
        this.mList = mList;
        this.mContext = mContext;
        this.mDetails = mDetails;
    }

    public PokemonTypeAdapter(List<Type> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout;
        if (viewType == 0)
            layout = R.layout.layout_pokemon_type_details;
        else
            layout = R.layout.layout_pokemon_type;

        return new ViewHolder(LayoutInflater.from(mContext).inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonTypeAdapter.ViewHolder holder, int position) {
        Type type = mList.get(position);
        holder.txtType.setText(type.getName());
    }

    @Override
    public int getItemViewType(int position) {
        if (mDetails)
            return 0;
        else
            return 1;

//        return super.getItemViewType(position);
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
