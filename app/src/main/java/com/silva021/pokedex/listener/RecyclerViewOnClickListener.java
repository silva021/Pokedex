package com.silva021.pokedex.listener;

import android.view.View;

import androidx.annotation.NonNull;

import com.silva021.pokedex.model.Pokemon;

public interface RecyclerViewOnClickListener {
    void onItemClick(@NonNull View view, @NonNull Pokemon pokemon);
}
