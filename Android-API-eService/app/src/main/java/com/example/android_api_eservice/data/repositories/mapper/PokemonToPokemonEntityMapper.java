package com.example.android_api_eservice.data.repositories.mapper;

import android.text.TextUtils;

import com.example.android_api_eservice.PokemonDetail;
import com.example.android_api_eservice.data.api.model.Pokemon;
import com.example.android_api_eservice.data.api.model.PokemonDetails;
import com.example.android_api_eservice.data.entity.PokemonEntity;

public class PokemonToPokemonEntityMapper {

    public PokemonEntity map(PokemonDetails pokemonDetails) {
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(pokemonDetails.getId());
        pokemonEntity.setName(pokemonDetails.getName());
        pokemonEntity.setFront_default(pokemonDetails.getSprites().getFront_default());
        return pokemonEntity;
    }

}
