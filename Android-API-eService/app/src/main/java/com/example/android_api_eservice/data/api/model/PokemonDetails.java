package com.example.android_api_eservice.data.api.model;

import java.util.List;

//This class represent the response of the API when requesting the detail of a Pokemon
public class PokemonDetails {
    private List<PokemonAbility> abilities;
    private String height;
    private String id;
    private String name;
    private Sprites sprites;
    private List<PokemonStat> stats;
    private List<PokemonType> types;
    private String weight;

    //Here is the unused details of a Pokemon we get from the API
    //private String base_experience;
    //private Forms forms;
    //private String game_indices;
    //private Held_items held_items;
    //private String is_default;
    //private String location_area_encounters;
    //private Moves moves;
    //private String order;
    //private Species species;


    public List<PokemonAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<PokemonAbility> abilities) {
        this.abilities = abilities;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

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

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public List<PokemonStat> getStats() {
        return stats;
    }

    public void setStats(List<PokemonStat> stats) {
        this.stats = stats;
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonType> types) {
        this.types = types;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

}
