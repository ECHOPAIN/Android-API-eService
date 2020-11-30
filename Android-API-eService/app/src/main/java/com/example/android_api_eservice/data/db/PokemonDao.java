package com.example.android_api_eservice.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.android_api_eservice.data.entity.PokemonEntity;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface PokemonDao {
    @Query("SELECT * from pokemonentity")
    Flowable<List<PokemonEntity>> getFavoritePokemons();

    @Insert
    Completable addPokemonToFavorites(PokemonEntity pokemonentity);

    @Query("DELETE FROM pokemonentity WHERE id = :id")
    Completable deletePokemonFromFavorites(String id);

    @Query("SELECT id from pokemonentity")
    Single<List<String>> getFavoriteIdList();
}
