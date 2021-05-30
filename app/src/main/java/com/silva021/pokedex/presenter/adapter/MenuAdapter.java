package com.silva021.pokedex.presenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.silva021.pokedex.R;
import com.silva021.pokedex.domain.model.menu.Menu;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private List<Menu> menuList;
    private OnClickListener listener;

    public MenuAdapter(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public void setData(List<Menu> menuList) {
        this.menuList.addAll(menuList);
        notifyDataSetChanged();
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {
        holder.onBind(position);
    }

    Menu getMenu(int position) {
        return menuList.get(position);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtItem)
        TextView txtItem;
        @BindView(R.id.cardViewLayout)
        CardView cardView;
        @BindView(R.id.imgPokeball)
        ImageView imgPokeball;
        @BindView(R.id.imgPokeballDetails)
        ImageView imgPokeballDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind(int position) {
            Menu menu = getMenu(position);

            txtItem.setText(menu.getTextItem());
            cardView.setCardBackgroundColor(menu.getColorBackground());
            imgPokeball.setColorFilter(menu.getColorPokeball());
            imgPokeballDetails.setColorFilter(menu.getColorPokeballDetails());
            cardView.setOnClickListener(v -> onClick(menu));
        }

        void onClick(Menu menu) {
            if (listener != null) {
                listener.onClickListener(menu);
            }
        }
    }

    public interface OnClickListener {
        void onClickListener(Menu menu);
    }
}
