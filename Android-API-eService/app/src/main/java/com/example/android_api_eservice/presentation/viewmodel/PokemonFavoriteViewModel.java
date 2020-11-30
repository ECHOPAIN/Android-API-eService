package com.example.android_api_eservice.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.android_api_eservice.data.entity.PokemonEntity;
import com.example.android_api_eservice.data.repositories.PokemonRepository;
import com.example.android_api_eservice.presentation.pokemon.favorite.adapter.PokemonDetailViewModel;
import com.example.android_api_eservice.presentation.pokemon.favorite.mapper.PokemonEntityToDetailViewModelMapper;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class PokemonFavoriteViewModel extends ViewModel {
    private PokemonRepository pokemonRepository;
    private CompositeDisposable compositeDisposable;
    private PokemonEntityToDetailViewModelMapper pokemonEntityToDetailViewModelMapper;
    private MutableLiveData<List<PokemonDetailViewModel>> favorites;
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();
    final MutableLiveData<Event<String>> pokemonAddedEvent = new MutableLiveData<Event<String>>();
    final MutableLiveData<Event<String>> pokemonDeletedEvent = new MutableLiveData<Event<String>>();

    //constructor
    public PokemonFavoriteViewModel(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.pokemonEntityToDetailViewModelMapper = new PokemonEntityToDetailViewModelMapper();
    }

    //add a Pokemon to the favortie by it's id
    public void addPokemonToFavorites(final String pokemonId) {
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

    //remove a Pokemon from the favortie by it's id
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

    //get the list of all the favorite Pokemon
    public MutableLiveData<List<PokemonDetailViewModel>> getFavorites() {
        isDataLoading.setValue(true);
        if (favorites == null) {
            favorites = new MutableLiveData<List<PokemonDetailViewModel>>();
            compositeDisposable.add(pokemonRepository.getFavoritePokemons()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new ResourceSubscriber<List<PokemonEntity>>() {

                        @Override
                        public void onNext(List<PokemonEntity> pokemonEntityList) {
                            isDataLoading.setValue(false);
                            favorites.setValue(pokemonEntityToDetailViewModelMapper.map(pokemonEntityList));
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            isDataLoading.setValue(false);
                        }

                        @Override
                        public void onComplete() {
                            //Do Nothing
                            isDataLoading.setValue(false);
                        }
                    }));

        }
        return favorites;
    }

    public MutableLiveData<Event<String>> getPokemonAddedEvent() {
        return pokemonAddedEvent;
    }

    public MutableLiveData<Event<String>> getPokemonDeletedEvent() {
        return pokemonDeletedEvent;
    }
}
