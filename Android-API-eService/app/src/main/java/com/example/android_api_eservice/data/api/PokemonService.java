package com.example.android_api_eservice.data.api;

import com.example.android_api_eservice.data.api.model.PokemonDetails;
import com.example.android_api_eservice.data.api.model.PokemonSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonService {
    @GET("https://pokeapi.co/api/v2/pokemon")
    Single<PokemonSearchResponse> getPokemons(@Query("offset") String offset,@Query("limit") String limit);

    @GET("https://pokeapi.co/api/v2/pokemon/{name}")
    Single<PokemonDetails> getPokemonDetail(@Path("name") String name);
}
