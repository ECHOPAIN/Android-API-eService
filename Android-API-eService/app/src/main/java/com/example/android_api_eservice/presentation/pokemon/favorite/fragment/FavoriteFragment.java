package com.example.android_api_eservice.presentation.pokemon.favorite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android_api_eservice.R;
import com.example.android_api_eservice.data.di.FakeDependencyInjection;
import com.example.android_api_eservice.presentation.pokemon.favorite.adapter.PokemonDetailActionInterface;
import com.example.android_api_eservice.presentation.pokemon.favorite.adapter.PokemonDetailAdapter;
import com.example.android_api_eservice.presentation.pokemon.favorite.adapter.PokemonDetailViewModel;
import com.example.android_api_eservice.presentation.viewmodel.Event;
import com.example.android_api_eservice.presentation.viewmodel.PokemonFavoriteViewModel;
import com.example.android_api_eservice.presentation.viewmodel.PokemonPokedexViewModel;

import java.util.List;

public class FavoriteFragment extends Fragment implements PokemonDetailActionInterface {
    private View view;
    private RecyclerView recyclerView;
    private PokemonDetailAdapter pokemonAdapter;
    private PokemonFavoriteViewModel pokemonFavoriteViewModel;
    private PokemonPokedexViewModel pokemonPokedexViewModel;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_favorite, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        registerViewModels();
    }

    private void setupRecyclerView() {
        recyclerView = view.findViewById(R.id.recycler_view);
        pokemonAdapter = new PokemonDetailAdapter(this);
        recyclerView.setAdapter(pokemonAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2, LinearLayoutManager.VERTICAL,false));
    }

    private void registerViewModels() {
        pokemonFavoriteViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(PokemonFavoriteViewModel.class);
        pokemonPokedexViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(PokemonPokedexViewModel.class);

        pokemonFavoriteViewModel.getFavorites().observe(getViewLifecycleOwner(), new Observer<List<PokemonDetailViewModel>>() {
            @Override
            public void onChanged(List<PokemonDetailViewModel> pokemonDetailViewModelList) {
                pokemonAdapter.bindViewModels(pokemonDetailViewModelList);
            }
        });

        pokemonFavoriteViewModel.getPokemonAddedEvent().observe(getViewLifecycleOwner(), new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                //Do nothing
            }
        });

        pokemonFavoriteViewModel.getPokemonDeletedEvent().observe(getViewLifecycleOwner(), new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                //Do nothing
            }
        });
    }

    @Override
    public void onRemoveFavorite(String pokemonId) {
        pokemonFavoriteViewModel.removePokemonFromFavorites(pokemonId);
        pokemonPokedexViewModel.removePokemonFromFavorites(pokemonId);
    }

}