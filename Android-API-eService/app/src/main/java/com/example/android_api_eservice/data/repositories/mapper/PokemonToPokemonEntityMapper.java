package com.example.android_api_eservice.data.repositories.mapper;

import android.text.TextUtils;

import com.example.android_api_eservice.PokemonDetail;
import com.example.android_api_eservice.data.api.model.Pokemon;
import com.example.android_api_eservice.data.api.model.PokemonDetails;
import com.example.android_api_eservice.data.api.model.Types;
import com.example.android_api_eservice.data.entity.PokemonEntity;

import java.util.ArrayList;

public class PokemonToPokemonEntityMapper {

    public PokemonEntity map(PokemonDetails pokemonDetails) {
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(pokemonDetails.getId());
        pokemonEntity.setName(pokemonDetails.getName());
        pokemonEntity.setFront_default(pokemonDetails.getSprites().getFront_default());
        pokemonEntity.setPrimaryType(pokemonDetails.getTypes().get(0).getType().getName());
        if(pokemonDetails.getTypes().get(1)!=null){
            pokemonEntity.setSecondaryType(pokemonDetails.getTypes().get(1).getType().getName());
        }else {
            pokemonEntity.setSecondaryType(null);
        }
        return pokemonEntity;
    }

}
