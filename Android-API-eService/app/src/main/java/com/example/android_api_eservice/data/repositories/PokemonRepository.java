package com.example.android_api_eservice.data.repositories;

import com.example.android_api_eservice.data.api.model.PokemonDetails;
import com.example.android_api_eservice.data.api.model.PokemonSearchResponse;
import com.example.android_api_eservice.data.repositories.local.PokemonLocalDataSource;
import com.example.android_api_eservice.data.repositories.remote.PokemonRemoteDataSource;


import io.reactivex.Single;

public class PokemonRepository {
    private PokemonLocalDataSource pokemonLocalDataSource;
    private PokemonRemoteDataSource pokemonRemoteDataSource;



    public PokemonRepository(PokemonLocalDataSource pokemonLocalDataSource, PokemonRemoteDataSource pokemonRemoteDataSource) {
        this.pokemonLocalDataSource = pokemonLocalDataSource;
        this.pokemonRemoteDataSource = pokemonRemoteDataSource;
    }


    public Single<PokemonSearchResponse> getPokemons(String offset, String limit){
        return pokemonRemoteDataSource.getPokemons(offset, limit);
    }

    public Single<PokemonDetails> getPokemonDetail(String name) {
        return pokemonRemoteDataSource.getPokemonDetail(name);
    }

}
