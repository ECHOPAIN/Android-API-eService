package com.example.android_api_eservice.presentation.pokemon.pokedex.adapter;

public class PokemonViewModel {
    private String id;
    private String name;
    private String front_default;
    private boolean isFavorite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFront_default() {
        return front_default;
    }

    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) { isFavorite = favorite; }
}
