package com.example.android_api_eservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.android_api_eservice.adapters.RecyclerViewAdapter;
import com.example.android_api_eservice.models.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Pokemon> pokemons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initImageBitmaps();
    }


    //default images to test
    private void initImageBitmaps(){
        pokemons.add(new Pokemon("1",   "bulbasaur",    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"));
        pokemons.add(new Pokemon("2",   "ivysaur",      "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"));
        pokemons.add(new Pokemon("3",   "venusaur",     "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png"));
        pokemons.add(new Pokemon("4",   "charmander",   "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"));
        pokemons.add(new Pokemon("5",   "charmeleon",   "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png"));
        pokemons.add(new Pokemon("6",   "charizard",    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png"));
        pokemons.add(new Pokemon("7",   "squirtle",     "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png"));
        pokemons.add(new Pokemon("8",   "wartortle",    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/8.png"));
        pokemons.add(new Pokemon("9",   "blastoise",    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png"));

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(pokemons,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}