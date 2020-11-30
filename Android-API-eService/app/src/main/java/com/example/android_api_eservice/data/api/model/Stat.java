package com.example.android_api_eservice.data.api.model;

//This class represent a stat of a Pokemon when requesting the API on the detail of a Pokemon
public class Stat {
    private String name;
    private String url;

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
}
