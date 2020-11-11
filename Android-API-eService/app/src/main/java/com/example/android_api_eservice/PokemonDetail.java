package com.example.android_api_eservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.android_api_eservice.data.api.model.PokemonDetails;
import com.example.android_api_eservice.data.di.FakeDependencyInjection;
import com.example.android_api_eservice.data.repositories.PokemonRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PokemonDetail extends AppCompatActivity {

    private TextView textViewPokemonName;
    private TextView textViewPokemonId;
    private ImageView imageView;
    private TextView textViewPokemonType;
    private TextView textViewPokemonHeight;
    private TextView textViewPokemonWeight;
    private TextView textViewPokemonHp;
    private TextView textViewPokemonAttack;
    private TextView textViewPokemonDefense;
    private TextView textViewPokemonSpecialAttack;
    private TextView textViewPokemonSpecialDefense;
    private TextView textViewPokemonSpeed;
    private ProgressBar progressBarPokemonHp;
    private ProgressBar progressBarPokemonAttack;
    private ProgressBar progressBarPokemonDefense;
    private ProgressBar progressBarPokemonSpecialAttack;
    private ProgressBar progressBarPokemonSpecialDefense;
    private ProgressBar progressBarPokemonSpeed;
    private LinearLayout detailLayout;
    private ProgressBar progressBar;
    private PokemonRepository pokemonRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        textViewPokemonName = findViewById(R.id.pokemon_detail_name);
        textViewPokemonId = findViewById(R.id.pokemon_detail_id);
        imageView = findViewById(R.id.pokemon_detail_image);
        textViewPokemonType = findViewById(R.id.pokemon_detail_type);
        //textViewPokemonHeight = findViewById(R.id.pokemon_detail_height);
        //textViewPokemonWeight = findViewById(R.id.pokemon_detail_weight);
        textViewPokemonHp = findViewById(R.id.pokemon_detail_hp_value);
        textViewPokemonAttack = findViewById(R.id.pokemon_detail_attack_value);
        textViewPokemonDefense = findViewById(R.id.pokemon_detail_defense_value);
        textViewPokemonSpecialAttack = findViewById(R.id.pokemon_detail_spatk_value);
        textViewPokemonSpecialDefense = findViewById(R.id.pokemon_detail_spdef_value);
        textViewPokemonSpeed = findViewById(R.id.pokemon_detail_speed_value);
        progressBarPokemonHp = findViewById(R.id.pokemon_detail_hp_bar);
        progressBarPokemonAttack = findViewById(R.id.pokemon_detail_attack_bar);
        progressBarPokemonDefense = findViewById(R.id.pokemon_detail_defense_bar);
        progressBarPokemonSpecialAttack = findViewById(R.id.pokemon_detail_spatk_bar);
        progressBarPokemonSpecialDefense = findViewById(R.id.pokemon_detail_spdef_bar);
        progressBarPokemonSpeed = findViewById(R.id.pokemon_detail_speed_bar);
        detailLayout = findViewById(R.id.detail_layout);
        progressBar = findViewById(R.id.pokemon_detail_progress_bar);

        pokemonRepository = FakeDependencyInjection.getPokemonRepository();



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

        //Name
        String name = pokemonDetails.getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        textViewPokemonName.setText(name);

        //id
        textViewPokemonId.setText("#"+pokemonDetails.getId());

        //Sprite
        Glide.with(this)
                .load(pokemonDetails.getSprites().getFront_default())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .circleCrop()
                .into(imageView);


        //Types
        if(pokemonDetails.getTypes().size()==1){
            textViewPokemonType.setText(pokemonDetails.getTypes().get(0).getType().getName().toUpperCase());
        }else{
            textViewPokemonType.setText(pokemonDetails.getTypes().get(0).getType().getName().toUpperCase() + " - " + pokemonDetails.getTypes().get(1).getType().getName().toUpperCase());
        }

        //set the background according to the primary type
        setBackground(detailLayout,pokemonDetails.getTypes().get(0).getType().getName());

        /*
        //Height
        String height = pokemonDetails.getHeight();
        if(height.length()<=1){
            height=0+height;
        }
        height=height.substring(0,height.length()-1)+"."+height.substring(height.length()-1,height.length());
        textViewPokemonHeight.setText("Height : "+height+"m");

        //Weight
        String weight = pokemonDetails.getWeight();
        if(weight.length()<=1){
            weight=0+weight;
        }
        weight=weight.substring(0,weight.length()-1)+"."+weight.substring(weight.length()-1,weight.length());
        textViewPokemonWeight.setText("Weight : "+weight+"kg");*/


        //Stats
        Integer maxValue = Math.max(Integer.valueOf(pokemonDetails.getStats().get(0).getBase_stat()),
                                    Math.max(Integer.valueOf(pokemonDetails.getStats().get(1).getBase_stat()),
                                            Math.max(Integer.valueOf(pokemonDetails.getStats().get(2).getBase_stat()),
                                                    Math.max(Integer.valueOf(pokemonDetails.getStats().get(3).getBase_stat()),
                                                            Math.max(Integer.valueOf(pokemonDetails.getStats().get(4).getBase_stat()),
                                                                    Integer.valueOf(pokemonDetails.getStats().get(5).getBase_stat()))))));
        //Hp
        textViewPokemonHp.setText(pokemonDetails.getStats().get(0).getBase_stat());
        progressBarPokemonHp.setMax(maxValue);
        progressBarPokemonHp.setProgress(Integer.valueOf(pokemonDetails.getStats().get(0).getBase_stat()));

        //Attack
        textViewPokemonAttack.setText(pokemonDetails.getStats().get(1).getBase_stat());
        progressBarPokemonAttack.setMax(maxValue);
        progressBarPokemonAttack.setProgress(Integer.valueOf(pokemonDetails.getStats().get(1).getBase_stat()));

        //Defense
        textViewPokemonDefense.setText(pokemonDetails.getStats().get(2).getBase_stat());
        progressBarPokemonDefense.setMax(maxValue);
        progressBarPokemonDefense.setProgress(Integer.valueOf(pokemonDetails.getStats().get(2).getBase_stat()));

        //Special Attack
        textViewPokemonSpecialAttack.setText(pokemonDetails.getStats().get(3).getBase_stat());
        progressBarPokemonSpecialAttack.setMax(maxValue);
        progressBarPokemonSpecialAttack.setProgress(Integer.valueOf(pokemonDetails.getStats().get(3).getBase_stat()));

        //Special Defense
        textViewPokemonSpecialDefense.setText(pokemonDetails.getStats().get(4).getBase_stat());
        progressBarPokemonSpecialDefense.setMax(maxValue);
        progressBarPokemonSpecialDefense.setProgress(Integer.valueOf(pokemonDetails.getStats().get(4).getBase_stat()));

        //Speed
        textViewPokemonSpeed.setText(pokemonDetails.getStats().get(5).getBase_stat());
        progressBarPokemonSpeed.setMax(maxValue);
        progressBarPokemonSpeed.setProgress(Integer.valueOf(pokemonDetails.getStats().get(5).getBase_stat()));


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

            default:
                linearLayout.setBackgroundResource(R.drawable.normal);
        }
    }
}