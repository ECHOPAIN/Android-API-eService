package com.example.android_api_eservice.data.repositories.remote;

import com.example.android_api_eservice.data.api.PokemonService;
import com.example.android_api_eservice.data.api.model.PokemonDetails;
import com.example.android_api_eservice.data.api.model.PokemonSearchResponse;

import io.reactivex.Single;

public class PokemonRemoteDataSource {
    private PokemonService pokemonService;

    public PokemonRemoteDataSource(PokemonService pokemonService){
        this.pokemonService=pokemonService;
    }

    public Single<PokemonSearchResponse> getAllPokemons(){
        return pokemonService.getAllPokemons();
    }
    public Single<PokemonDetails> getPokemonDetail(String name){
        return pokemonService.getPokemonDetail(name);
    }


}
