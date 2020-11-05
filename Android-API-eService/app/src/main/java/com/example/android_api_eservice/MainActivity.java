package com.example.android_api_eservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> names = new ArrayList<>();
    private List<String> imageUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initImageBitmaps();
    }


    //default images to test
    private void initImageBitmaps(){
        imageUrls.add("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png");
        names.add("bulbasaur");

        imageUrls.add("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png");
        names.add("ivysaur");

        imageUrls.add("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png");
        names.add("venusaur");

        imageUrls.add("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png");
        names.add("charmander");

        imageUrls.add("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png");
        names.add("charmeleon");

        imageUrls.add("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png");
        names.add("charizard");

        imageUrls.add("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png");
        names.add("squirtle");

        imageUrls.add("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/8.png");
        names.add("wartortle");

        imageUrls.add("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png");
        names.add("blastoise");

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(names, imageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}