package com.example.android_api_eservice.presentation.pokemondetail;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android_api_eservice.R;
import com.example.android_api_eservice.data.api.model.PokemonDetails;

public class PokemonDetailStats {
    View view;
    private TextView textViewPokemonHp;
    private TextView textViewPokemonAttack;
    private TextView textViewPokemonDefense;
    private TextView textViewPokemonSpecialAttack;
    private TextView textViewPokemonSpecialDefense;
    private TextView textViewPokemonSpeed;
    private ProgressBar progressBarPokemonHp;
    private ProgressBar progressBarPokemonAttack;
    private ProgressBar progressBarPokemonDefense;
    private ProgressBar progressBarPokemonSpecialAttack;
    private ProgressBar progressBarPokemonSpecialDefense;
    private ProgressBar progressBarPokemonSpeed;

    public PokemonDetailStats(View view) {
        this.view=view;
        textViewPokemonHp = view.findViewById(R.id.pokemon_detail_hp_value);
        textViewPokemonAttack = view.findViewById(R.id.pokemon_detail_attack_value);
        textViewPokemonDefense = view.findViewById(R.id.pokemon_detail_defense_value);
        textViewPokemonSpecialAttack = view.findViewById(R.id.pokemon_detail_spatk_value);
        textViewPokemonSpecialDefense = view.findViewById(R.id.pokemon_detail_spdef_value);
        textViewPokemonSpeed = view.findViewById(R.id.pokemon_detail_speed_value);
        progressBarPokemonHp = view.findViewById(R.id.pokemon_detail_hp_bar);
        progressBarPokemonAttack = view.findViewById(R.id.pokemon_detail_attack_bar);
        progressBarPokemonDefense = view.findViewById(R.id.pokemon_detail_defense_bar);
        progressBarPokemonSpecialAttack = view.findViewById(R.id.pokemon_detail_spatk_bar);
        progressBarPokemonSpecialDefense = view.findViewById(R.id.pokemon_detail_spdef_bar);
        progressBarPokemonSpeed = view.findViewById(R.id.pokemon_detail_speed_bar);
    }

    public void bind(PokemonDetails pokemonDetails) {
        //Stats
        Integer maxValue = Math.max(Integer.valueOf(pokemonDetails.getStats().get(0).getBase_stat()),
                Math.max(Integer.valueOf(pokemonDetails.getStats().get(1).getBase_stat()),
                        Math.max(Integer.valueOf(pokemonDetails.getStats().get(2).getBase_stat()),
                                Math.max(Integer.valueOf(pokemonDetails.getStats().get(3).getBase_stat()),
                                        Math.max(Integer.valueOf(pokemonDetails.getStats().get(4).getBase_stat()),
                                                Integer.valueOf(pokemonDetails.getStats().get(5).getBase_stat()))))));


        //Hp
        bindStat(textViewPokemonHp,pokemonDetails.getStats().get(0).getBase_stat(),progressBarPokemonHp,Integer.valueOf(pokemonDetails.getStats().get(0).getBase_stat()),maxValue);

        //Attack
        bindStat(textViewPokemonAttack,pokemonDetails.getStats().get(1).getBase_stat(),progressBarPokemonAttack,Integer.valueOf(pokemonDetails.getStats().get(1).getBase_stat()),maxValue);

        //Defense
        bindStat(textViewPokemonDefense,pokemonDetails.getStats().get(2).getBase_stat(),progressBarPokemonDefense,Integer.valueOf(pokemonDetails.getStats().get(2).getBase_stat()),maxValue);

        //Special Attack
        bindStat(textViewPokemonSpecialAttack,pokemonDetails.getStats().get(3).getBase_stat(),progressBarPokemonSpecialAttack,Integer.valueOf(pokemonDetails.getStats().get(3).getBase_stat()),maxValue);

        //Special Defense
        bindStat(textViewPokemonSpecialDefense,pokemonDetails.getStats().get(4).getBase_stat(),progressBarPokemonSpecialDefense,Integer.valueOf(pokemonDetails.getStats().get(4).getBase_stat()),maxValue);

        //Speed
        bindStat(textViewPokemonSpeed,pokemonDetails.getStats().get(5).getBase_stat(),progressBarPokemonSpeed,Integer.valueOf(pokemonDetails.getStats().get(5).getBase_stat()),maxValue);
    }

    private void bindStat(TextView textView, String baseStat, ProgressBar progressBar, Integer baseStatValue, Integer maxValue) {
        textView.setText(baseStat);
        progressBar.setMax(maxValue);
        progressBar.setProgress(0);

        //ProgressBar Animation
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", progressBar.getProgress(), baseStatValue);
        animation.setDuration(500);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }
}
