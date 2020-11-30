package com.example.android_api_eservice.presentation.pokemondetail;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.android_api_eservice.R;
import com.example.android_api_eservice.data.api.model.PokemonDetails;

public class PokemonDetailSprites {
    View view;
    private ImageView imageViewFront;
    private ImageView imageViewBack;
    private ImageView imageViewFrontFemale;
    private ImageView imageViewBackFemale;
    private ImageView imageViewFrontShiny;
    private ImageView imageViewBackShiny;
    private ImageView imageViewFrontShinyFemale;
    private ImageView imageViewBackShinyFemale;
    private LinearLayout layoutFrontFemale;
    private LinearLayout layoutBackFemale;
    private LinearLayout layoutFrontShinyFemale;
    private LinearLayout layoutBackShinyFemale;
    private TextView textViewFrontMale;
    private TextView textViewBackMale;
    private TextView textViewShinyFrontMale;
    private TextView textViewShinyBackMale;

    public PokemonDetailSprites(View view) {
        this.view=view;
        imageViewFront= view.findViewById(R.id.pokemon_detail_front_default);
        imageViewBack= view.findViewById(R.id.pokemon_detail_back_default);
        imageViewFrontFemale= view.findViewById(R.id.pokemon_detail_front_female);
        imageViewBackFemale= view.findViewById(R.id.pokemon_detail_back_female);
        imageViewFrontShiny= view.findViewById(R.id.pokemon_detail_front_shiny);
        imageViewBackShiny= view.findViewById(R.id.pokemon_detail_back_shiny);
        imageViewFrontShinyFemale= view.findViewById(R.id.pokemon_detail_front_shiny_female);
        imageViewBackShinyFemale= view.findViewById(R.id.pokemon_detail_back_shiny_female);
        layoutFrontFemale = view.findViewById(R.id.pokemon_detail_front_female_layout);
        layoutBackFemale = view.findViewById(R.id.pokemon_detail_back_female_layout);
        layoutFrontShinyFemale = view.findViewById(R.id.pokemon_detail_front_shiny_female_layout);
        layoutBackShinyFemale = view.findViewById(R.id.pokemon_detail_back_shiny_female_layout);
        textViewFrontMale = view.findViewById(R.id.pokemon_detail_front_default_male_text);
        textViewBackMale = view.findViewById(R.id.pokemon_detail_back_default_male_text);
        textViewShinyFrontMale = view.findViewById(R.id.pokemon_detail_front_shiny_male_text);
        textViewShinyBackMale = view.findViewById(R.id.pokemon_detail_back_shiny_male_text);
    }

    public void bind(PokemonDetails pokemonDetails) {
        //Sprites
        Boolean differenceBetweenGender = (pokemonDetails.getSprites().getFront_female()!=null);

        Glide.with(view)
                .load(pokemonDetails.getSprites().getFront_default())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewFront);
        Glide.with(view)
                .load(pokemonDetails.getSprites().getBack_default())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewBack);
        Glide.with(view)
                .load(pokemonDetails.getSprites().getFront_shiny())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewFrontShiny);
        Glide.with(view)
                .load(pokemonDetails.getSprites().getBack_shiny())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewBackShiny);

        if(differenceBetweenGender) {
            Glide.with(view)
                    .load(pokemonDetails.getSprites().getFront_female())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewFrontFemale);
            Glide.with(view)
                    .load(pokemonDetails.getSprites().getBack_female())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewBackFemale);
            Glide.with(view)
                    .load(pokemonDetails.getSprites().getFront_shiny_female())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewFrontShinyFemale);
            Glide.with(view)
                    .load(pokemonDetails.getSprites().getBack_shiny_female())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewBackShinyFemale);
        }else{
            //hide female layouts
            layoutFrontFemale.setVisibility(View.GONE);
            layoutBackFemale.setVisibility(View.GONE);
            layoutFrontShinyFemale.setVisibility(View.GONE);
            layoutBackShinyFemale.setVisibility(View.GONE);

            //Male title should now be Male/Female
            textViewFrontMale.setText("Male/Female");
            textViewBackMale.setText("Male/Female");
            textViewShinyFrontMale.setText("Male/Female");
            textViewShinyBackMale.setText("Male/Female");
        }
    }
}
