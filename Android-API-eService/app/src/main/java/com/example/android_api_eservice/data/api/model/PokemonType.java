package com.example.android_api_eservice.data.api.model;

//This class represent a PokemonType when requesting the API on the detail of a Pokemon
public class PokemonType {
    private String slot;
    private Type type;

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
