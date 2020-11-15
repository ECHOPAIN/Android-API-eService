package com.example.android_api_eservice.data.repositories.mapper;

import com.example.android_api_eservice.data.api.model.PokemonDetails;
import com.example.android_api_eservice.data.entity.PokemonEntity;

public class PokemonToPokemonEntityMapper {

    public PokemonEntity map(PokemonDetails pokemonDetails) {
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(pokemonDetails.getId());
        pokemonEntity.setName(pokemonDetails.getName());
        pokemonEntity.setFront_default(pokemonDetails.getSprites().getFront_default());
        pokemonEntity.setAttackValue(pokemonDetails.getStats().get(1).getBase_stat());
        pokemonEntity.setDefenseValue(pokemonDetails.getStats().get(2).getBase_stat());
        pokemonEntity.setSpecialAttackValue(pokemonDetails.getStats().get(3).getBase_stat());
        pokemonEntity.setSpecialDefenseValue(pokemonDetails.getStats().get(4).getBase_stat());
        pokemonEntity.setSpeedValue(pokemonDetails.getStats().get(5).getBase_stat());
        pokemonEntity.setHpValue(pokemonDetails.getStats().get(0).getBase_stat());
        pokemonEntity.setHeight(pokemonDetails.getHeight());
        pokemonEntity.setWeight(pokemonDetails.getWeight());
        pokemonEntity.setPrimaryType(pokemonDetails.getTypes().get(0).getType().getName());
        if(pokemonDetails.getTypes().size()>1){
            pokemonEntity.setSecondaryType(pokemonDetails.getTypes().get(1).getType().getName());
        }else {
            pokemonEntity.setSecondaryType(null);
        }
        return pokemonEntity;
    }

}
