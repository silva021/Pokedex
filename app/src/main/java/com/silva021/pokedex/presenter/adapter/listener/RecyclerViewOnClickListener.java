package com.silva021.pokedex.presenter.adapter.listener;

import android.view.View;

import androidx.annotation.NonNull;

import com.silva021.pokedex.domain.model.Pokemon;

public interface RecyclerViewOnClickListener {
    void onItemClick(@NonNull View view, @NonNull Pokemon pokemon);
}
