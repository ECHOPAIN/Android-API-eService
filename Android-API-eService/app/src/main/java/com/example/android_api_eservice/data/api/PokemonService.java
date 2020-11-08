package com.example.android_api_eservice.data.api;

import com.example.android_api_eservice.data.api.model.PokemonDetails;
import com.example.android_api_eservice.data.api.model.PokemonSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonService {
    @GET("https://pokeapi.co/api/v2/pokemon")
    Single<PokemonSearchResponse> getAllPokemons();

    @GET("https://pokeapi.co/api/v2/pokemon/{name}")
    Single<PokemonDetails> getPokemonDetail(@Path("name") String name);
}
