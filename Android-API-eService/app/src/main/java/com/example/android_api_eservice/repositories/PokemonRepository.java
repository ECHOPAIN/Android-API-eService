package com.example.android_api_eservice.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.android_api_eservice.models.Pokemon;
import com.example.android_api_eservice.repositories.local.PokemonLocalDataSource;

import java.util.ArrayList;
import java.util.List;

public class PokemonRepository {

    private static PokemonRepository instance;
    private PokemonLocalDataSource pokemonLocalDataSource = new PokemonLocalDataSource();
    private ArrayList<Pokemon> dataSet = new ArrayList<>();

    public static PokemonRepository getInstance(){
        if(instance == null){
            instance = new PokemonRepository();
        }
        return instance ;
    }


    //Pretend to get data from a webservice
    public MutableLiveData<List<Pokemon>> getAllPokemons(){
        //for now get local datas
        //should later call the api
        dataSet.addAll(pokemonLocalDataSource.setPokemonsFromLocal());

        MutableLiveData<List<Pokemon>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }


}
