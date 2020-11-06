package com.example.android_api_eservice.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_api_eservice.R;
import com.example.android_api_eservice.adapters.RecyclerViewAdapter;
import com.example.android_api_eservice.models.Pokemon;
import com.example.android_api_eservice.viewmodels.PokemonsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PokedexFragment extends Fragment {
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private PokemonsViewModel pokemonsViewModel;
    private RecyclerViewAdapter adapter;



    public PokedexFragment() {
        // Required empty public constructor
    }

    public static PokedexFragment newInstance() {
        return new PokedexFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    private void initRecyclerView(View view){
        adapter = new RecyclerViewAdapter(pokemonsViewModel.getPokemons().getValue(),view.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_pokedex, container, false);



        fab = view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.recycler_view);

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

        initRecyclerView(view);


        final RecyclerView.LayoutManager linearLayoutManager = recyclerView.getLayoutManager();
        // set a GridLayoutManager with 3 number of columns , vertical gravity and false value for reverseLayout to show the items from start to end
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),3, LinearLayoutManager.VERTICAL,false);
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

        return view;
    }
}