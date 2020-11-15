package com.example.android_api_eservice;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.android_api_eservice.data.api.model.Abilities;
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
    private ImageView imageViewFront;
    private ImageView imageViewBack;
    private ImageView imageViewFrontFemale;
    private ImageView imageViewBackFemale;
    private ImageView imageViewFrontShiny;
    private ImageView imageViewBackShiny;
    private ImageView imageViewFrontShinyFemale;
    private ImageView imageViewBackShinyFemale;
    private LinearLayout layoutFrontFemale;
    private LinearLayout layoutBackFemale;
    private LinearLayout layoutFrontShinyFemale;
    private LinearLayout layoutBackShinyFemale;
    private TextView textViewPokemonType;
    private TextView textViewFrontMale;
    private TextView textViewBackMale;
    private TextView textViewShinyFrontMale;
    private TextView textViewShinyBackMale;
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
    private LinearLayout abilitiesLayout;
    private PokemonRepository pokemonRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
        pokemonRepository = FakeDependencyInjection.getPokemonRepository();

        textViewPokemonName = findViewById(R.id.pokemon_detail_name);
        textViewPokemonId = findViewById(R.id.pokemon_detail_id);
        imageView = findViewById(R.id.pokemon_detail_image);
        imageViewFront= findViewById(R.id.pokemon_detail_front_default);
        imageViewBack= findViewById(R.id.pokemon_detail_back_default);
        imageViewFrontFemale= findViewById(R.id.pokemon_detail_front_female);
        imageViewBackFemale= findViewById(R.id.pokemon_detail_back_female);
        imageViewFrontShiny= findViewById(R.id.pokemon_detail_front_shiny);
        imageViewBackShiny= findViewById(R.id.pokemon_detail_back_shiny);
        imageViewFrontShinyFemale= findViewById(R.id.pokemon_detail_front_shiny_female);
        imageViewBackShinyFemale= findViewById(R.id.pokemon_detail_back_shiny_female);
        textViewPokemonType = findViewById(R.id.pokemon_detail_type);
        layoutFrontFemale = findViewById(R.id.pokemon_detail_front_female_layout);
        layoutBackFemale = findViewById(R.id.pokemon_detail_back_female_layout);
        layoutFrontShinyFemale = findViewById(R.id.pokemon_detail_front_shiny_female_layout);
        layoutBackShinyFemale = findViewById(R.id.pokemon_detail_back_shiny_female_layout);
        textViewFrontMale = findViewById(R.id.pokemon_detail_front_default_male_text);
        textViewBackMale = findViewById(R.id.pokemon_detail_back_default_male_text);
        textViewShinyFrontMale = findViewById(R.id.pokemon_detail_front_shiny_male_text);
        textViewShinyBackMale = findViewById(R.id.pokemon_detail_back_shiny_male_text);
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
        abilitiesLayout = findViewById(R.id.pokemon_detail_abilities_layout);

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
                .into(imageView);

        //Types
        if(pokemonDetails.getTypes().size()==1){
            textViewPokemonType.setText(pokemonDetails.getTypes().get(0).getType().getName().toUpperCase());
        }else{
            textViewPokemonType.setText(pokemonDetails.getTypes().get(0).getType().getName().toUpperCase() + " - " + pokemonDetails.getTypes().get(1).getType().getName().toUpperCase());
        }
        //set the background according to the primary type
        setBackground(detailLayout,pokemonDetails.getTypes().get(0).getType().getName());

        //Stats
        Integer maxValue = Math.max(Integer.valueOf(pokemonDetails.getStats().get(0).getBase_stat()),
                                    Math.max(Integer.valueOf(pokemonDetails.getStats().get(1).getBase_stat()),
                                            Math.max(Integer.valueOf(pokemonDetails.getStats().get(2).getBase_stat()),
                                                    Math.max(Integer.valueOf(pokemonDetails.getStats().get(3).getBase_stat()),
                                                            Math.max(Integer.valueOf(pokemonDetails.getStats().get(4).getBase_stat()),
                                                                    Integer.valueOf(pokemonDetails.getStats().get(5).getBase_stat()))))));


        //Hp
        bindStat(textViewPokemonHp,pokemonDetails.getStats().get(0).getBase_stat(),progressBarPokemonHp,Integer.valueOf(pokemonDetails.getStats().get(0).getBase_stat()),maxValue);

        //Attack
        bindStat(textViewPokemonAttack,pokemonDetails.getStats().get(1).getBase_stat(),progressBarPokemonAttack,Integer.valueOf(pokemonDetails.getStats().get(1).getBase_stat()),maxValue);

        //Defense
        bindStat(textViewPokemonDefense,pokemonDetails.getStats().get(2).getBase_stat(),progressBarPokemonDefense,Integer.valueOf(pokemonDetails.getStats().get(2).getBase_stat()),maxValue);

        //Special Attack
        bindStat(textViewPokemonSpecialAttack,pokemonDetails.getStats().get(3).getBase_stat(),progressBarPokemonSpecialAttack,Integer.valueOf(pokemonDetails.getStats().get(3).getBase_stat()),maxValue);

        //Special Defense
        bindStat(textViewPokemonSpecialDefense,pokemonDetails.getStats().get(4).getBase_stat(),progressBarPokemonSpecialDefense,Integer.valueOf(pokemonDetails.getStats().get(4).getBase_stat()),maxValue);

        //Speed
        bindStat(textViewPokemonSpeed,pokemonDetails.getStats().get(5).getBase_stat(),progressBarPokemonSpeed,Integer.valueOf(pokemonDetails.getStats().get(5).getBase_stat()),maxValue);

        //Abilities
        for(Abilities abilities : pokemonDetails.getAbilities()) {
            addAbilityToLayout(abilitiesLayout, abilities);
        }

        //Sprites
        Boolean differenceBetweenGender = (pokemonDetails.getSprites().getFront_female()!=null);

        Glide.with(this)
                .load(pokemonDetails.getSprites().getFront_default())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewFront);
        Glide.with(this)
                .load(pokemonDetails.getSprites().getBack_default())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewBack);
        Glide.with(this)
                .load(pokemonDetails.getSprites().getFront_shiny())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewFrontShiny);
        Glide.with(this)
                .load(pokemonDetails.getSprites().getBack_shiny())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewBackShiny);

        if(differenceBetweenGender) {
            Glide.with(this)
                    .load(pokemonDetails.getSprites().getFront_female())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewFrontFemale);
            Glide.with(this)
                    .load(pokemonDetails.getSprites().getBack_female())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewBackFemale);
            Glide.with(this)
                    .load(pokemonDetails.getSprites().getFront_shiny_female())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewFrontShinyFemale);
            Glide.with(this)
                    .load(pokemonDetails.getSprites().getBack_shiny_female())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewBackShinyFemale);
        }else{
            //hide female layouts
            layoutFrontFemale.setVisibility(View.GONE);
            layoutBackFemale.setVisibility(View.GONE);
            layoutFrontShinyFemale.setVisibility(View.GONE);
            layoutBackShinyFemale.setVisibility(View.GONE);

            //Male title should now be Male/Female
            textViewFrontMale.setText("Male/Female");
            textViewBackMale.setText("Male/Female");
            textViewShinyFrontMale.setText("Male/Female");
            textViewShinyBackMale.setText("Male/Female");
        }

        detailLayout.setVisibility(View.VISIBLE);
    }

    private void addAbilityToLayout(LinearLayout abilitiesLayout, Abilities abilities) {
        RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(10,0,0,10);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(lparams);

        if(abilities.isIs_hidden()){
            //layout_alignParentLeft
            TextView textViewHidden=new TextView(this);
            textViewHidden.setLayoutParams(lparams);
            textViewHidden.setGravity(Gravity.LEFT);
            textViewHidden.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
            textViewHidden.setText("Hidden");

            relativeLayout.addView(textViewHidden);
        }

        TextView textView=new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
        String name = abilities.getAbility().getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        textView.setText(name);

        relativeLayout.addView(textView);

        abilitiesLayout.addView(relativeLayout);
    }

    private void bindStat(TextView textView, String baseStat, ProgressBar progressBar, Integer baseStatValue, Integer maxValue) {
        textView.setText(baseStat);
        progressBar.setMax(maxValue);
        progressBar.setProgress(0);

        //ProgressBar Animation
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", progressBar.getProgress(), baseStatValue);
        animation.setDuration(500);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
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