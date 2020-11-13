package com.example.android_api_eservice.presentation.pokemon.favorite.mapper;

import com.example.android_api_eservice.data.entity.PokemonEntity;
import com.example.android_api_eservice.presentation.pokemon.favorite.adapter.PokemonDetailViewModel;

import java.util.ArrayList;
import java.util.List;

public class PokemonEntityToDetailViewModelMapper {

    private PokemonDetailViewModel map(PokemonEntity pokemonEntity) {
        PokemonDetailViewModel pokemonDetailViewModel = new PokemonDetailViewModel();
        pokemonDetailViewModel.setId(pokemonEntity.getId());
        pokemonDetailViewModel.setName(pokemonEntity.getName());
        pokemonDetailViewModel.setFront_default(pokemonEntity.getFront_default());
        pokemonDetailViewModel.setPrimaryType(pokemonEntity.getPrimaryType());
        pokemonDetailViewModel.setSecondaryType(pokemonEntity.getSecondaryType());
        pokemonDetailViewModel.setAttackValue(pokemonEntity.getAttackValue());
        pokemonDetailViewModel.setDefenseValue(pokemonEntity.getDefenseValue());
        pokemonDetailViewModel.setSpecialAttackValue(pokemonEntity.getSpecialAttackValue());
        pokemonDetailViewModel.setSpecialDefenseValue(pokemonEntity.getSpecialDefenseValue());
        pokemonDetailViewModel.setHpValue(pokemonEntity.getHpValue());
        pokemonDetailViewModel.setSpeedValue(pokemonEntity.getSpeedValue());
        pokemonDetailViewModel.setHeight(pokemonEntity.getHeight());
        pokemonDetailViewModel.setWeight(pokemonEntity.getWeight());
        return pokemonDetailViewModel;
    }

    public List<PokemonDetailViewModel> map(List<PokemonEntity> pokemonList) {
        List<PokemonDetailViewModel> pokemonItemViewModelList = new ArrayList<>();
        for (PokemonEntity pokemon : pokemonList) {
            pokemonItemViewModelList.add(map(pokemon));
        }
        return pokemonItemViewModelList;
    }
}
