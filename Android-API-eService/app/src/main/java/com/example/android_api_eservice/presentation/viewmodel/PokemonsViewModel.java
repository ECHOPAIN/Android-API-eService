package com.example.android_api_eservice.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_api_eservice.presentation.pokemon.pokedex.mapper.PokemonToViewModelMapper;
import com.example.android_api_eservice.presentation.pokemon.pokedex.adapter.PokemonViewItem;
import com.example.android_api_eservice.data.api.model.PokemonSearchResponse;
import com.example.android_api_eservice.data.repositories.PokemonRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PokemonsViewModel extends ViewModel {
    private final int MAX_POKEMON=151;
    private final int MAX_POKEMON_PER_CALL=18;
    private PokemonRepository pokemonRepository;
    private CompositeDisposable compositeDisposable;
    private PokemonToViewModelMapper pokemonToViewModelMapper;
    private MutableLiveData<List<PokemonViewItem>> pokemons = new MutableLiveData<List<PokemonViewItem>>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();

    public PokemonsViewModel(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.pokemonToViewModelMapper = new PokemonToViewModelMapper();
        loadMorePokemons();
    }


    public MutableLiveData<List<PokemonViewItem>> getPokemons() {
        return pokemons;
    }
    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public void loadMorePokemons() {
        int offset = 0;
        int limit;
        if(getPokemons().getValue()!=null){
            if(getPokemons().getValue().size()>=MAX_POKEMON){
                //we already retreived the 151 pokemons
                return;
            }else{
                offset=getPokemons().getValue().size();
            }
        }

        limit = Math.min(MAX_POKEMON_PER_CALL,MAX_POKEMON-offset);


        isDataLoading.setValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(pokemonRepository.getPokemons(String.valueOf(offset),String.valueOf(limit))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<PokemonSearchResponse>() {
                    @Override
                    public void onSuccess(PokemonSearchResponse pokemonSearchResponse) {
                        List<PokemonViewItem> pokemonsTmp = new ArrayList<>();
                        if(getPokemons().getValue()!=null){
                            pokemonsTmp.addAll(getPokemons().getValue());
                        }
                        pokemonsTmp.addAll(pokemonToViewModelMapper.map(pokemonSearchResponse.getPokemons()));
                        pokemons.setValue(pokemonsTmp);
                        isDataLoading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        //Yet, do not do nothing in this app
                        System.out.println(e.toString());
                        isDataLoading.setValue(false);
                    }

                }));
    }

}
