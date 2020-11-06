package com.example.android_api_eservice.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.android_api_eservice.models.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonRepository {

    private static PokemonRepository instance;
    private ArrayList<Pokemon> dataSet = new ArrayList<>();

    public static PokemonRepository getInstance(){
        if(instance == null){
            instance = new PokemonRepository();
        }
        return instance ;
    }


    //Pretend to get data from a webservice
    public MutableLiveData<List<Pokemon>> getPokemons(){
        //for now get local datas
        //should later call the api
        setPokemons();

        MutableLiveData<List<Pokemon>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setPokemons(){
        dataSet.add(new Pokemon("1",   "bulbasaur",    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"));
        dataSet.add(new Pokemon("2",   "ivysaur",      "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"));
        dataSet.add(new Pokemon("3",   "venusaur",     "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png"));
        dataSet.add(new Pokemon("4",   "charmander",   "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"));
        dataSet.add(new Pokemon("5",   "charmeleon",   "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png"));
        dataSet.add(new Pokemon("6",   "charizard",    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png"));
        dataSet.add(new Pokemon("7",   "squirtle",     "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png"));
        dataSet.add(new Pokemon("8",   "wartortle",    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/8.png"));
        dataSet.add(new Pokemon("9",   "blastoise",    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png"));
    }
}
