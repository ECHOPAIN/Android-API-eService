package com.example.android_api_eservice.presentation.pokemondetail;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.android_api_eservice.R;
import com.example.android_api_eservice.data.api.model.PokemonAbility;
import com.example.android_api_eservice.data.api.model.PokemonDetails;

public class PokemonDetailAbilities {
    View view;
    private LinearLayout abilitiesLayout;

    public PokemonDetailAbilities(View view) {
        this.view=view;
        abilitiesLayout = view.findViewById(R.id.pokemon_detail_abilities_layout);
    }

    public void bind(PokemonDetails pokemonDetails) {
        //bind the Pokemon abilities
        for(PokemonAbility pokemonAbility : pokemonDetails.getAbilities()) {
            addAbilityToLayout(abilitiesLayout, pokemonAbility);
        }
    }

    //bind the Pokemon abilities
    private void addAbilityToLayout(LinearLayout abilitiesLayout, PokemonAbility pokemonAbility) {
        RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(10,0,0,10);
        RelativeLayout relativeLayout = new RelativeLayout(view.getContext());
        relativeLayout.setLayoutParams(lparams);

        if(pokemonAbility.isIs_hidden()){
            //layout_alignParentLeft
            TextView textViewHidden=new TextView(view.getContext());
            textViewHidden.setLayoutParams(lparams);
            textViewHidden.setGravity(Gravity.LEFT);
            textViewHidden.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
            textViewHidden.setText("Hidden");

            relativeLayout.addView(textViewHidden);
        }

        TextView textView=new TextView(view.getContext());
        textView.setLayoutParams(lparams);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
        String name = pokemonAbility.getAbility().getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        textView.setText(name);

        relativeLayout.addView(textView);

        abilitiesLayout.addView(relativeLayout);
    }
}
