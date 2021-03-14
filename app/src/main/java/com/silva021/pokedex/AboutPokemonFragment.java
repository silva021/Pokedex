package com.silva021.pokedex;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.silva021.pokedex.model.Abilities;
import com.silva021.pokedex.model.Ability;
import com.silva021.pokedex.model.Pokemon;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutPokemonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutPokemonFragment extends Fragment {
    TextView txtAbilities;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AboutPokemonFragment() {
        // Required empty public constructor
    }

    public static AboutPokemonFragment newInstance(String param1, String param2) {
        AboutPokemonFragment fragment = new AboutPokemonFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_pokemon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtAbilities = view.findViewById(R.id.txtAbilities);

        Bundle data = getArguments();
        Pokemon pokemon = (Pokemon) data.getSerializable("object");
        updateView(pokemon);
    }

    private void updateView(Pokemon pokemon) {
        updateAbilities(txtAbilities, pokemon.getAbilities());
    }

    private void updateAbilities(TextView textView, Abilities abilities) {
        String text = "";
        for (Ability ability : abilities.getList()){
            text += ability.getName() + ", ";
        }

        textView.setText(text);
    }
}