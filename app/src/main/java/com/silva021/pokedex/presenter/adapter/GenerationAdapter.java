package com.silva021.pokedex.presenter.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.silva021.pokedex.R;
import com.silva021.pokedex.domain.model.menu.MenuGeneration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenerationAdapter extends RecyclerView.Adapter<GenerationAdapter.ViewHolder> {
    private List<MenuGeneration> menuGenerations;
    private OnClickListener listener;

    public GenerationAdapter(List<MenuGeneration> menuGenerations) {
        this.menuGenerations = menuGenerations;
    }

    public void setData(List<MenuGeneration> menuGenerations) {
        this.menuGenerations.addAll(menuGenerations);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(parent.getContext(), LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_generation, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GenerationAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    private MenuGeneration getMenuGeneration(int position) {
        return menuGenerations.get(position);
    }

    @Override
    public int getItemCount() {
        return menuGenerations.size();
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.cardViewLayout)
        CardView cardViewLayout;
        @BindView(R.id.imgPokemon)
        ImageView imgPokemon;
        Context context;

        public ViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        public void bind(int position) {
            MenuGeneration menuGeneration = getMenuGeneration(position);
            imgPokemon.setImageDrawable(context.getResources().getDrawable(menuGeneration.getIdImageGeneration()));
            txtName.setText(menuGeneration.getGenerationName());
            cardViewLayout.setOnClickListener(view -> onClick(menuGeneration));
        }

        private void onClick(MenuGeneration menuGeneration) {
            if (listener != null) {
                listener.onClickListener(menuGeneration);
            }
        }

    }

    public interface OnClickListener {
        void onClickListener(MenuGeneration menu);
    }
}
