package com.silva021.pokedex.presenter.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.silva021.pokedex.R;
import com.silva021.pokedex.domain.model.Type;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PokemonTypeAdapter extends RecyclerView.Adapter<PokemonTypeAdapter.ViewHolder> {
    List<Type> mList;
    Context mContext;
    boolean mDetails = false;
    long mColor;

    public PokemonTypeAdapter(List<Type> mList, Context mContext, long mColor) {
        this.mList = mList;
        this.mContext = mContext;
        this.mColor = mColor;
    }

    public PokemonTypeAdapter(List<Type> mList, Context mContext, long mColor, boolean mDetails) {
        this.mList = mList;
        this.mContext = mContext;
        this.mDetails = mDetails;
        this.mColor = mColor;
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull PokemonTypeAdapter.ViewHolder holder, int position) {
        Type type = mList.get(position);
        holder.txtType.setText(type.getName());
        holder.linearLayout.getBackground().setTint((int) mColor);
    }

    @Override
    public int getItemViewType(int position) {
        if (mDetails)
            return 0;
        else
            return 1;

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtType)
        TextView txtType;
        @BindView(R.id.linearLayout)
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
