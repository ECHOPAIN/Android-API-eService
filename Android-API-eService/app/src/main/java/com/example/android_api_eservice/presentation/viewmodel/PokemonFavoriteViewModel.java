package com.example.android_api_eservice.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_api_eservice.data.repositories.PokemonRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class PokemonFavoriteViewModel extends ViewModel {

    private PokemonRepository pokemonRepository;
    private CompositeDisposable compositeDisposable;


    final MutableLiveData<Event<String>> pokemonAddedEvent = new MutableLiveData<Event<String>>();
    final MutableLiveData<Event<String>> pokemonDeletedEvent = new MutableLiveData<Event<String>>();

    public PokemonFavoriteViewModel(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    public void addPokemonToFavorite(final String pokemonId) {
        compositeDisposable.add(pokemonRepository.addPokemonToFavorites(pokemonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        pokemonAddedEvent.setValue(new Event<String>(pokemonId));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    public void removePokemonFromFavorites(final String pokemonId) {
        compositeDisposable.add(pokemonRepository.removePokemonFromFavorites(pokemonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        pokemonDeletedEvent.setValue(new Event<String>(pokemonId));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }
}
