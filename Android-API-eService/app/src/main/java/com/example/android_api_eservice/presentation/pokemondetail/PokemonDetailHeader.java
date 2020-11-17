package com.example.android_api_eservice.presentation.pokemondetail;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.android_api_eservice.R;
import com.example.android_api_eservice.data.api.model.PokemonDetails;

public class PokemonDetailHeader {
    View view;
    private TextView textViewPokemonName;
    private TextView textViewPokemonId;
    private ImageView imageView;
    private TextView textViewPokemonType;

    public PokemonDetailHeader(View view) {
        this.view=view;
        textViewPokemonName = view.findViewById(R.id.pokemon_detail_name);
        textViewPokemonId = view.findViewById(R.id.pokemon_detail_id);
        imageView = view.findViewById(R.id.pokemon_detail_image);
        textViewPokemonType = view.findViewById(R.id.pokemon_detail_type);
    }

    public void bind(PokemonDetails pokemonDetails) {
        //Name
        String name = pokemonDetails.getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        textViewPokemonName.setText(name);

        //id
        textViewPokemonId.setText("#"+pokemonDetails.getId());

        //Sprite
        Glide.with(view)
                .load(pokemonDetails.getSprites().getFront_default())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);

        //Types
        if(pokemonDetails.getTypes().size()==1){
            textViewPokemonType.setText(pokemonDetails.getTypes().get(0).getType().getName().toUpperCase());
        }else{
            textViewPokemonType.setText(pokemonDetails.getTypes().get(0).getType().getName().toUpperCase() + " - " + pokemonDetails.getTypes().get(1).getType().getName().toUpperCase());
        }
    }
}
