package com.example.android_api_eservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.android_api_eservice.adapters.RecyclerViewAdapter;
import com.example.android_api_eservice.models.Pokemon;
import com.example.android_api_eservice.viewmodels.PokemonsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private PokemonsViewModel pokemonsViewModel;
    private RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycler_view);

        pokemonsViewModel = new ViewModelProvider(this).get(PokemonsViewModel.class);
        //retreive the data from the repository
        pokemonsViewModel.init();
        //observe the changes
        pokemonsViewModel.getPokemons().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                adapter.notifyDataSetChanged();
            }
        });

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

    private void initRecyclerView(){
        adapter = new RecyclerViewAdapter(pokemonsViewModel.getPokemons().getValue(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}