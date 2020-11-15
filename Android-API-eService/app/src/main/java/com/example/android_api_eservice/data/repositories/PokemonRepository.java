package com.example.android_api_eservice.data.repositories;

import com.example.android_api_eservice.data.api.model.Pokemon;
import com.example.android_api_eservice.data.api.model.PokemonDetails;
import com.example.android_api_eservice.data.api.model.PokemonSearchResponse;
import com.example.android_api_eservice.data.entity.PokemonEntity;
import com.example.android_api_eservice.data.repositories.local.PokemonLocalDataSource;
import com.example.android_api_eservice.data.repositories.mapper.PokemonToPokemonEntityMapper;
import com.example.android_api_eservice.data.repositories.remote.PokemonRemoteDataSource;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class PokemonRepository {
    private PokemonLocalDataSource pokemonLocalDataSource;
    private PokemonRemoteDataSource pokemonRemoteDataSource;
    private PokemonToPokemonEntityMapper pokemonToPokemonEntityMapper;

    public PokemonRepository(PokemonLocalDataSource pokemonLocalDataSource, PokemonRemoteDataSource pokemonRemoteDataSource,PokemonToPokemonEntityMapper pokemonToPokemonEntityMapper) {
        this.pokemonLocalDataSource = pokemonLocalDataSource;
        this.pokemonRemoteDataSource = pokemonRemoteDataSource;
        this.pokemonToPokemonEntityMapper = pokemonToPokemonEntityMapper;
    }

    public Single<PokemonSearchResponse> getPokemons(String offset, String limit){
        return pokemonRemoteDataSource.getPokemons(offset, limit)
                .zipWith(pokemonLocalDataSource.getFavoriteIdList(), new BiFunction<PokemonSearchResponse, List<String>, PokemonSearchResponse>() {
                    @Override
                    public PokemonSearchResponse apply(PokemonSearchResponse pokemonSearchResponse, List<String> idList) throws Exception {
                        for (Pokemon pokemon : pokemonSearchResponse.getPokemons()) {
                            if (idList.contains(pokemon.getId())) {
                                pokemon.setFavorite();
                            }
                        }
                        return pokemonSearchResponse;
                    }
                });
    }

    public Single<PokemonDetails> getPokemonDetail(String id) {
        return pokemonRemoteDataSource.getPokemonDetail(id);
    }

    public Completable addPokemonToFavorites(String pokemonId) {
        return pokemonRemoteDataSource.getPokemonDetail(pokemonId)
                .map(new Function<PokemonDetails, PokemonEntity>() {
                    @Override
                    public PokemonEntity apply(PokemonDetails pokemonDetails) throws Exception {
                        return pokemonToPokemonEntityMapper.map(pokemonDetails);
                    }
                })
                .flatMapCompletable(new Function<PokemonEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(PokemonEntity pokemonEntity) throws Exception {
                        return pokemonLocalDataSource.addPokemonToFavorites(pokemonEntity);
                    }
                });
    }

    public Completable removePokemonFromFavorites(String pokemonId) {
        return pokemonLocalDataSource.deletePokemonFromFavorites(pokemonId);
    }

    public Flowable<List<PokemonEntity>> getFavoritePokemons() {
        return pokemonLocalDataSource.getFavoritePokemons();
    }
}
