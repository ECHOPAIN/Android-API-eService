package com.example.android_api_eservice.data.api.model;

public class Pokemon {
    private String name;
    private String url;
    private boolean isFavorite;

    public String getId() {
        String[] urlSplitted = url.split("/");
        return urlSplitted[urlSplitted.length - 1];
    }

    public String getFront_default() {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+getId()+".png";
    }


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
}
