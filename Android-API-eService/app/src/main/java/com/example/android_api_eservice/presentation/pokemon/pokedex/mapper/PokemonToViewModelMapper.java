package com.example.android_api_eservice.presentation.pokemon.pokedex.mapper;

import com.example.android_api_eservice.data.api.model.Pokemon;
import com.example.android_api_eservice.presentation.pokemon.pokedex.adapter.PokemonViewItem;

import java.util.ArrayList;
import java.util.List;

public class PokemonToViewModelMapper {

    private PokemonViewItem map(Pokemon pokemon) {
        PokemonViewItem pokemonViewItem = new PokemonViewItem();
        pokemonViewItem.setId(pokemon.getId());
        pokemonViewItem.setName(pokemon.getName());
        pokemonViewItem.setFront_default(pokemon.getFront_default());
        pokemonViewItem.setFavorite(pokemon.isFavorite());
        return pokemonViewItem;
    }

    public List<PokemonViewItem> map(List<Pokemon> pokemonList) {
        List<PokemonViewItem> pokemonViewItemList = new ArrayList<>();
        for (Pokemon pokemon : pokemonList) {
            pokemonViewItemList.add(map(pokemon));
        }
        return pokemonViewItemList;
    }
}
