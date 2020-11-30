package com.example.android_api_eservice.presentation.pokemon.pokedex.mapper;

import com.example.android_api_eservice.data.api.model.Pokemon;
import com.example.android_api_eservice.presentation.pokemon.pokedex.adapter.PokemonViewModel;
import java.util.ArrayList;
import java.util.List;

public class PokemonToViewModelMapper {

    private PokemonViewModel map(Pokemon pokemon) {
        PokemonViewModel pokemonViewModel = new PokemonViewModel();
        pokemonViewModel.setId(pokemon.getId());
        pokemonViewModel.setName(pokemon.getName());
        pokemonViewModel.setFront_default(pokemon.getFront_default());
        pokemonViewModel.setFavorite(pokemon.isFavorite());
        return pokemonViewModel;
    }

    public List<PokemonViewModel> map(List<Pokemon> pokemonList) {
        List<PokemonViewModel> pokemonViewModelList = new ArrayList<>();
        for (Pokemon pokemon : pokemonList) {
            pokemonViewModelList.add(map(pokemon));
        }
        return pokemonViewModelList;
    }
}
