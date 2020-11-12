package com.example.android_api_eservice.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_api_eservice.data.repositories.PokemonRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

private final PokemonRepository pokemonRepository;

        public ViewModelFactory(PokemonRepository pokemonRepository) {
                this.pokemonRepository = pokemonRepository;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
                if (modelClass.isAssignableFrom(PokemonsViewModel.class)) {
                        return (T) new PokemonsViewModel(pokemonRepository);
                }
                if (modelClass.isAssignableFrom(PokemonFavoriteViewModel.class)) {
                        return (T) new PokemonFavoriteViewModel(pokemonRepository);
                }
                throw new IllegalArgumentException("Unknown ViewModel class");
        }
}
