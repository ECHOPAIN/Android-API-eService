package com.example.android_api_eservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
    private RelativeLayout relativeLayout;
    private ProgressBar progressBar;
    private PokemonRepository pokemonRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        textViewPokemonName = findViewById(R.id.pokemon_detail_name);
        imageView = findViewById(R.id.pokemon_detail_image);
        textViewPokemonType = findViewById(R.id.pokemon_detail_type);
        textViewPokemonHeight = findViewById(R.id.pokemon_detail_height);
        textViewPokemonWeight = findViewById(R.id.pokemon_detail_weight);
        textViewPokemonHp = findViewById(R.id.pokemon_detail_hp);
        textViewPokemonAttack = findViewById(R.id.pokemon_detail_attack);
        textViewPokemonDefense = findViewById(R.id.pokemon_detail_defense);
        textViewPokemonSpecialAttack = findViewById(R.id.pokemon_detail_special_attack);
        textViewPokemonSpecialDefense = findViewById(R.id.pokemon_detail_special_defense);
        textViewPokemonSpeed = findViewById(R.id.pokemon_detail_speed);
        relativeLayout = findViewById(R.id.detail_layout);
        progressBar = findViewById(R.id.pokemon_detail_progress_bar);

        pokemonRepository = FakeDependencyInjection.getPokemonRepository();



        fetchAndDisplayPokemonDetails();
    }

    private void fetchAndDisplayPokemonDetails() {

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
        textViewPokemonWeight.setText("Weight : "+weight+"kg");

        //Hp
        String hpName = pokemonDetails.getStats().get(0).getStat().getName();
        hpName = hpName.substring(0, 1).toUpperCase() + hpName.substring(1);
        textViewPokemonHp.setText(hpName + " : " + pokemonDetails.getStats().get(0).getBase_stat());

        //Attack
        String attackName = pokemonDetails.getStats().get(1).getStat().getName();
        attackName = attackName.substring(0, 1).toUpperCase() + attackName.substring(1);
        textViewPokemonAttack.setText(attackName + " : " + pokemonDetails.getStats().get(1).getBase_stat());

        //Defense
        String defenseName = pokemonDetails.getStats().get(2).getStat().getName();
        defenseName = defenseName.substring(0, 1).toUpperCase() + defenseName.substring(1);
        textViewPokemonDefense.setText(defenseName + " : " + pokemonDetails.getStats().get(2).getBase_stat());

        //Special Attack
        String specialAttackName = pokemonDetails.getStats().get(3).getStat().getName();
        specialAttackName = specialAttackName.substring(0, 1).toUpperCase() + specialAttackName.substring(1);
        textViewPokemonSpecialAttack.setText(specialAttackName + " : " + pokemonDetails.getStats().get(3).getBase_stat());

        //Special Defense
        String specialDefenseName = pokemonDetails.getStats().get(4).getStat().getName();
        specialDefenseName = specialDefenseName.substring(0, 1).toUpperCase() + specialDefenseName.substring(1);
        textViewPokemonSpecialDefense.setText(specialDefenseName + " : " + pokemonDetails.getStats().get(4).getBase_stat());

        //Speed
        String speedName = pokemonDetails.getStats().get(5).getStat().getName();
        speedName = speedName.substring(0, 1).toUpperCase() + speedName.substring(1);
        textViewPokemonSpeed.setText(speedName + " : " + pokemonDetails.getStats().get(5).getBase_stat());


        relativeLayout.setVisibility(View.VISIBLE);
    }
}