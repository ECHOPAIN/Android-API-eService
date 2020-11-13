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
