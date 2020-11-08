package com.example.android_api_eservice.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_api_eservice.presentation.pokemon.pokedex.mapper.PokemonToViewModelMapper;
import com.example.android_api_eservice.presentation.pokemon.pokedex.adapter.PokemonViewItem;
import com.example.android_api_eservice.data.api.model.PokemonSearchResponse;
import com.example.android_api_eservice.data.repositories.PokemonRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PokemonsViewModel extends ViewModel {
    private PokemonRepository pokemonRepository;
    private CompositeDisposable compositeDisposable;
    private PokemonToViewModelMapper pokemonToViewModelMapper;
    private MutableLiveData<List<PokemonViewItem>> pokemons = new MutableLiveData<List<PokemonViewItem>>();

    public PokemonsViewModel(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.pokemonToViewModelMapper = new PokemonToViewModelMapper();
        searchPokemons();
    }


    public MutableLiveData<List<PokemonViewItem>> getPokemons() {
        return pokemons;
    }

    public void searchPokemons() {
        compositeDisposable.clear();
        compositeDisposable.add(pokemonRepository.getAllPokemons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<PokemonSearchResponse>() {
                    @Override
                    public void onSuccess(PokemonSearchResponse pokemonSearchResponse) {
                        pokemons.setValue(pokemonToViewModelMapper.map(pokemonSearchResponse.getPokemons()));

                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        //Yet, do not do nothing in this app
                        System.out.println(e.toString());
                    }

                }));
    }

}
