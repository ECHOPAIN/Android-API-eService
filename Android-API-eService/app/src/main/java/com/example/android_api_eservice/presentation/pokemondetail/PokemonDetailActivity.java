package com.example.android_api_eservice.presentation.pokemondetail;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android_api_eservice.R;
import com.example.android_api_eservice.data.api.model.PokemonDetails;
import com.example.android_api_eservice.data.di.FakeDependencyInjection;
import com.example.android_api_eservice.data.repositories.PokemonRepository;
import com.example.android_api_eservice.presentation.util.Util;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PokemonDetailActivity extends AppCompatActivity {
    private LinearLayout detailLayout;
    private ProgressBar progressBar;
    private PokemonRepository pokemonRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
        pokemonRepository = FakeDependencyInjection.getPokemonRepository();
        detailLayout = findViewById(R.id.detail_layout);
        progressBar = findViewById(R.id.pokemon_detail_progress_bar);

        fetchAndDisplayPokemonDetails();
    }

    private void fetchAndDisplayPokemonDetails() {
        detailLayout.setVisibility(View.GONE);
        if(getIntent().hasExtra("pokemonId")) {
            String pokemonId = getIntent().getStringExtra(("pokemonId"));
            progressBar.setVisibility(View.VISIBLE);
            compositeDisposable.clear();
            compositeDisposable.add(pokemonRepository.getPokemonDetail(pokemonId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<PokemonDetails>() {

                        @Override
                        public void onSuccess(PokemonDetails requestPokemonDetails) {
                            PokemonDetails pokemonDetails = requestPokemonDetails;
                            progressBar.setVisibility(View.GONE);
                            bind(pokemonDetails);
                        }

                        @Override
                        public void onError(Throwable e) {
                            // handle the error case
                            Toast.makeText(getApplicationContext(), R.string.error_message, Toast.LENGTH_SHORT).show();
                            System.out.println(e.toString());
                            progressBar.setVisibility(View.GONE);
                            finish();
                        }

                    }));
        }

    }

    private void bind(PokemonDetails pokemonDetails) {
        //set the background according to the primary type
        detailLayout.setBackgroundResource(Util.getBackgroundFromType(pokemonDetails.getTypes().get(0).getType().getName()));

        //Deleguate the binding to the appropriate class
        PokemonDetailHeader pokemonDetailHeader = new PokemonDetailHeader(findViewById(R.id.pokemon_detail_header));
        pokemonDetailHeader.bind(pokemonDetails);
        PokemonDetailStats pokemonDetailStats = new PokemonDetailStats(findViewById(R.id.pokemon_detail_stats));
        pokemonDetailStats.bind(pokemonDetails);
        PokemonDetailAbilities pokemonDetailAbilities = new PokemonDetailAbilities(findViewById(R.id.pokemon_detail_abilities));
        pokemonDetailAbilities.bind(pokemonDetails);
        PokemonDetailSprites pokemonDetailSprites = new PokemonDetailSprites(findViewById(R.id.pokemon_detail_sprites));
        pokemonDetailSprites.bind(pokemonDetails);

        detailLayout.setVisibility(View.VISIBLE);
    }

}