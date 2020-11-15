package com.example.android_api_eservice.data.api.model;

import java.util.List;

public class PokemonDetails {
    private List<Abilities> abilities;
    private String base_experience;
    //private Forms forms;
    //private String game_indices;
    private String height;
    //private Held_items held_items;
    private String id;
    private String is_default;
    private String location_area_encounters;
    //private Moves moves;
    private String name;
    private String order;
    //private Species species;
    private Sprites sprites;
    private List<Stats> stats;
    private List<Types> types;
    private String weight;


    public List<Abilities> getAbilities() { return abilities; }

    public void setAbilities(List<Abilities> abilities) { this.abilities = abilities; }

    public String getBase_experience() { return base_experience; }

    public void setBase_experience(String base_experience) { this.base_experience = base_experience; }

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

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getLocation_area_encounters() {
        return location_area_encounters;
    }

    public void setLocation_area_encounters(String location_area_encounters) { this.location_area_encounters = location_area_encounters; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) { this.sprites = sprites; }

    public List<Stats> getStats() {
        return stats;
    }

    public void setStats(List<Stats> stats) {
        this.stats = stats;
    }

    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> types) {
        this.types = types;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
