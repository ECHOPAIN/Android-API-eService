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

    public Single<PokemonSearchResponse> getPokemons(String offset, String limit){
        return pokemonService.getPokemons(offset, limit);
    }
    public Single<PokemonDetails> getPokemonDetail(String id){
        return pokemonService.getPokemonDetail(id);
    }


}
