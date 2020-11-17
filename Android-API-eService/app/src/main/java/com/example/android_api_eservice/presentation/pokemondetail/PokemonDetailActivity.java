package com.example.android_api_eservice.presentation.pokemondetail;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.example.android_api_eservice.R;
import com.example.android_api_eservice.data.api.model.PokemonDetails;
import com.example.android_api_eservice.data.di.FakeDependencyInjection;
import com.example.android_api_eservice.data.repositories.PokemonRepository;
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
                            //Yet, do not do nothing in this app
                            System.out.println(e.toString());
                            progressBar.setVisibility(View.GONE);
                        }

                    }));
        }

    }

    private void bind(PokemonDetails pokemonDetails) {
        //set the background according to the primary type
        setBackground(detailLayout,pokemonDetails.getTypes().get(0).getType().getName());

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

    private void setBackground(LinearLayout linearLayout, String type) {
        switch(type.toLowerCase()) {
            case "normal":
                linearLayout.setBackgroundResource(R.drawable.normal);
                break;
            case "fighting":
                linearLayout.setBackgroundResource(R.drawable.fighting);
                break;
            case "poison":
                linearLayout.setBackgroundResource(R.drawable.poison);
                break;
            case "ground":
                linearLayout.setBackgroundResource(R.drawable.ground);
                break;
            case "rock":
                linearLayout.setBackgroundResource(R.drawable.rock);
                break;
            case "bug":
                linearLayout.setBackgroundResource(R.drawable.bug);
                break;
            case "ghost":
                linearLayout.setBackgroundResource(R.drawable.ghost);
                break;
            case "steel":
                linearLayout.setBackgroundResource(R.drawable.steel);
                break;
            case "fire":
                linearLayout.setBackgroundResource(R.drawable.fire);
                break;
            case "water":
                linearLayout.setBackgroundResource(R.drawable.water);
                break;
            case "grass":
                linearLayout.setBackgroundResource(R.drawable.grass);
                break;
            case "electric":
                linearLayout.setBackgroundResource(R.drawable.electric);
                break;
            case "psychic":
                linearLayout.setBackgroundResource(R.drawable.psychic);
                break;
            case "ice":
                linearLayout.setBackgroundResource(R.drawable.ice);
                break;
            case "dragon":
                linearLayout.setBackgroundResource(R.drawable.dragon);
                break;
            case "dark":
                linearLayout.setBackgroundResource(R.drawable.dark);
                break;
            case "fairy":
                linearLayout.setBackgroundResource(R.drawable.fairy);
                break;
            case "fly":
                linearLayout.setBackgroundResource(R.drawable.fly);
                break;

            default:
                linearLayout.setBackgroundResource(R.drawable.normal);
        }
    }
}