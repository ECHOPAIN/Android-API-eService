package com.example.android_api_eservice.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_api_eservice.models.Pokemon;
import com.example.android_api_eservice.repositories.PokemonRepository;

import java.util.List;

public class PokemonsViewModel extends ViewModel {

    private MutableLiveData<List<Pokemon>> pokemons;
    private PokemonRepository pokemonRepository;
    public void init(){
        if(pokemons!=null){
            //we already retrieved the data
            return;
        }
        pokemonRepository = PokemonRepository.getInstance();
        pokemons = pokemonRepository.getPokemons();
    }

    public LiveData<List<Pokemon>> getPokemons() {
        return pokemons;
    }

}
