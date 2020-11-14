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

import com.example.android_api_eservice.data.di.FakeDependencyInjection;
import com.example.android_api_eservice.presentation.pokemon.pokedex.adapter.PokemonActionInterface;
import com.example.android_api_eservice.presentation.pokemon.pokedex.adapter.PokemonGridAdapter;
import com.example.android_api_eservice.presentation.pokemon.pokedex.adapter.PokemonListAdapter;
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
    private PokemonListAdapter pokemonListAdapter;
    private PokemonGridAdapter pokemonGridAdapter;
    final RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3, LinearLayoutManager.VERTICAL,false);
    private PokemonFavoriteViewModel pokemonFavoriteViewModel;

    public PokedexFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_pokedex, container, false);

        fab = view.findViewById(R.id.fab);


        //Hangle gridView / listView change
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (recyclerView.getLayoutManager().equals(gridLayoutManager)) {
                    recyclerView.setAdapter(pokemonListAdapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    fab.setImageResource(R.drawable.grid_display);
                }else{
                    recyclerView.setAdapter(pokemonGridAdapter);
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
                pokemonListAdapter.bindViewModels(pokemonViewItems);
                pokemonGridAdapter.bindViewModels(pokemonViewItems);
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
        pokemonListAdapter = new PokemonListAdapter(this);
        pokemonGridAdapter = new PokemonGridAdapter(this);
        recyclerView.setAdapter(pokemonListAdapter);
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