package com.example.android_api_eservice.data.api.model;

//This class represent a basic Pokemon of the Pokemon list when requesting the API on listing the Pokemons
public class Pokemon {
    private String name;
    private String url;
    private boolean isFavorite;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFavorite() {
        isFavorite = true;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    //This method parse the url to get the Id
    public String getId() {
        String[] urlSplitted = url.split("/");
        return urlSplitted[urlSplitted.length - 1];
    }

    //This method use the getId method in order to provide an image without requesting further details for each Pokemon from the API
    public String getFront_default() {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+getId()+".png";
    }
}
