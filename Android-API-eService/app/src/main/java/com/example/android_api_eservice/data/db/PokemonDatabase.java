package com.example.android_api_eservice.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android_api_eservice.data.entity.PokemonEntity;

@Database(entities = {PokemonEntity.class}, version = 1)
public abstract class PokemonDatabase extends RoomDatabase{
        public abstract PokemonDao pokemonDao();
}
