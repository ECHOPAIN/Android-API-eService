package com.example.android_api_eservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.android_api_eservice.adapters.RecyclerViewAdapter;
import com.example.android_api_eservice.models.Pokemon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private List<Pokemon> pokemons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycler_view);


        initImageBitmaps();

        initRecyclerView();


        final RecyclerView.LayoutManager linearLayoutManager = recyclerView.getLayoutManager();
        // set a GridLayoutManager with 3 number of columns , vertical gravity and false value for reverseLayout to show the items from start to end
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (recyclerView.getLayoutManager().equals(gridLayoutManager)) {
                    recyclerView.setLayoutManager(linearLayoutManager);
                    fab.setImageResource(R.drawable.grid_display);
                }else{
                    recyclerView.setLayoutManager(gridLayoutManager);
                    fab.setImageResource(R.drawable.list_display);
                }
            }
        });
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
    }

    private void initRecyclerView(){
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(pokemons,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}