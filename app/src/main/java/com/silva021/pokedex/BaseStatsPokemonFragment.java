package com.silva021.pokedex;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.silva021.pokedex.model.Pokemon;

public class BaseStatsPokemonFragment extends Fragment {
    private ProgressBar progressAttack, progressDefense, progressSpecialAttack, progressSpecialDefense, progressSpeed, progressTotal, progressHP;
    private TextView txtAttack, txtDefense, txtSpecialAttack, txtSpecialDefense, txtSpeed, txtTotal, txtHP;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BaseStatsPokemonFragment() {

    }

    public static BaseStatsPokemonFragment newInstance(String param1, String param2) {
        BaseStatsPokemonFragment fragment = new BaseStatsPokemonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stats_pokemon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressAttack = view.findViewById(R.id.progressAttack);
        progressDefense = view.findViewById(R.id.progressDefense);
        progressHP = view.findViewById(R.id.progressHP);
        progressSpecialAttack = view.findViewById(R.id.progressSpecialAttack);
        progressSpecialDefense = view.findViewById(R.id.progressSpecialDefense);
        progressSpeed = view.findViewById(R.id.progressSpeed);
        progressTotal = view.findViewById(R.id.progressTotal);

        txtAttack = view.findViewById(R.id.txtAttack);
        txtDefense = view.findViewById(R.id.txtDefense);
        txtHP = view.findViewById(R.id.txtHP);
        txtSpecialAttack = view.findViewById(R.id.txtSpecialAttack);
        txtSpecialDefense = view.findViewById(R.id.txtSpecialDefense);
        txtSpeed = view.findViewById(R.id.txtSpeed);
        txtTotal = view.findViewById(R.id.txtTotal);

        Bundle data = getArguments();
        Pokemon pokemon = (Pokemon) data.getSerializable("object");
        updateView(pokemon);
    }

    private void updateView(Pokemon pokemon) {
        updateView(progressHP, txtHP, pokemon.getStats().getHP());
        updateView(progressAttack, txtAttack, pokemon.getStats().getAttack());
        updateView(progressSpecialAttack, txtSpecialAttack, pokemon.getStats().getSpecialAttack());
        updateView(progressDefense, txtDefense, pokemon.getStats().getDefense());
        updateView(progressSpecialDefense, txtSpecialDefense, pokemon.getStats().getSpecialDefense());
        updateView(progressSpeed, txtSpeed, pokemon.getStats().getSpeed());
        updateView(progressTotal, txtTotal, pokemon.getStats().getTotal());
    }

    private void setDrawable(ProgressBar progressBar, int value) {
        if (value <= 50)
            progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_red));
        else
            progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_green));

    }

    private void updateView(ProgressBar progressBar, TextView textView, int value) {
        progressBar.setProgress(value);
        textView.setText(String.valueOf(value));
        setDrawable(progressBar, value);
    }
}