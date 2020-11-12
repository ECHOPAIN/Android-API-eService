package com.example.android_api_eservice.presentation.pokemon.pokedex.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android_api_eservice.data.di.FakeDependencyInjection;
import com.example.android_api_eservice.presentation.pokemon.pokedex.adapter.PokemonActionInterface;
import com.example.android_api_eservice.presentation.pokemon.pokedex.adapter.PokemonAdapter;
import com.example.android_api_eservice.presentation.pokemon.pokedex.adapter.PokemonViewItem;
import com.example.android_api_eservice.R;
import com.example.android_api_eservice.presentation.viewmodel.PokemonFavoriteViewModel;
import com.example.android_api_eservice.presentation.viewmodel.PokemonsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PokedexFragment extends Fragment implements PokemonActionInterface {
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private PokemonsViewModel pokemonsViewModel;
    private View view;
    private PokemonAdapter pokemonAdapter;
    final RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    private PokemonFavoriteViewModel pokemonFavoriteViewModel;

    public PokedexFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_pokedex, container, false);



        //Setting up FAB (gridview and linearview)
        fab = view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.recycler_view);

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        progressBar = view.findViewById(R.id.progress_bar);
        registerViewModels();
    }

    private void registerViewModels() {
        pokemonsViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(PokemonsViewModel.class);
        pokemonFavoriteViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(PokemonFavoriteViewModel.class);

        pokemonsViewModel.getPokemons().observe(getViewLifecycleOwner(), new Observer<List<PokemonViewItem>>() {
            @Override
            public void onChanged(List<PokemonViewItem> pokemonViewItems) {
                pokemonAdapter.bindViewModels(pokemonViewItems);
            }
        });

        pokemonsViewModel.getIsDataLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isDataLoading) {
                progressBar.setVisibility(isDataLoading ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = view.findViewById(R.id.recycler_view);
        pokemonAdapter = new PokemonAdapter(this);
        recyclerView.setAdapter(pokemonAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        //if we reach the bottom of the recyclerview we should load more pokemons
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    pokemonsViewModel.loadMorePokemons();
                }
            }
        });
    }


    @Override
    public void onFavoriteToggle(String pokemonId, boolean isFavorite) {
        if (isFavorite) {
            pokemonFavoriteViewModel.addPokemonToFavorite(pokemonId);
        } else {
            pokemonFavoriteViewModel.removePokemonFromFavorites(pokemonId);
        }
    }
}