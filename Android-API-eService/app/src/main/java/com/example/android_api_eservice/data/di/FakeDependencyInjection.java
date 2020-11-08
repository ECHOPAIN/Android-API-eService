package com.example.android_api_eservice.data.di;

import android.content.Context;

import com.example.android_api_eservice.presentation.viewmodel.ViewModelFactory;
import com.example.android_api_eservice.data.api.PokemonService;
import com.example.android_api_eservice.data.repositories.PokemonRepository;
import com.example.android_api_eservice.data.repositories.local.PokemonLocalDataSource;
import com.example.android_api_eservice.data.repositories.remote.PokemonRemoteDataSource;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


//I use a fake dependency injection class because we haven't see anything else in Android class yet
public class FakeDependencyInjection {


    private static PokemonService pokemonService;
    private static Retrofit retrofit;
    private static Gson gson;
    private static PokemonRepository pokemonRepository;
    private static Context applicationContext;
    private static ViewModelFactory viewModelFactory;



    public static ViewModelFactory getViewModelFactory() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelFactory(getPokemonRepository());
        }
        return viewModelFactory;
    }


    public static PokemonRepository getPokemonRepository() {
        if (pokemonRepository == null) {
            pokemonRepository = new PokemonRepository(
                    new PokemonLocalDataSource(),
                    new PokemonRemoteDataSource(getPokemonService())
            );
        }
        return pokemonRepository;
    }

    public static PokemonService getPokemonService() {
        if (pokemonService == null) {
            pokemonService = getRetrofit().create(PokemonService.class);
        }
        return pokemonService;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }

}
