package com.example.android_api_eservice.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonSearchResponse {
    @SerializedName("results")
    List<Pokemon> pokemons;

    public List<Pokemon> getPokemons() {
        return pokemons;
    }
}
