package com.example.android_api_eservice.data.repositories.local;

import com.example.android_api_eservice.data.api.model.Pokemon;
import com.example.android_api_eservice.data.db.PokemonDatabase;
import com.example.android_api_eservice.data.entity.PokemonEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class PokemonLocalDataSource {

    private PokemonDatabase pokemonDatabase;

    public PokemonLocalDataSource(PokemonDatabase pokemonDatabase) {
        this.pokemonDatabase = pokemonDatabase;
    }

    public Flowable<List<PokemonEntity>> loadFavorites() {
        return pokemonDatabase.pokemonDao().loadFavorites();
    }

    public CompletableSource addPokemonToFavorites(PokemonEntity pokemonEntity) {
        return pokemonDatabase.pokemonDao().addPokemonToFavorites(pokemonEntity);
    }

    public Completable deletePokemonFromFavorites(String id) {
        return pokemonDatabase.pokemonDao().deletePokemonFromFavorites(id);
    }

    public Single<List<String>> getFavoriteIdList() {
        return pokemonDatabase.pokemonDao().getFavoriteIdList();
    }
}
