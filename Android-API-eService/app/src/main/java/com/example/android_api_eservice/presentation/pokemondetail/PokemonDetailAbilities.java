package com.example.android_api_eservice.presentation.pokemondetail;

import android.animation.ObjectAnimator;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android_api_eservice.R;
import com.example.android_api_eservice.data.api.model.Abilities;
import com.example.android_api_eservice.data.api.model.PokemonDetails;

public class PokemonDetailAbilities {
    View view;
    private LinearLayout abilitiesLayout;

    public PokemonDetailAbilities(View view) {
        this.view=view;
        abilitiesLayout = view.findViewById(R.id.pokemon_detail_abilities_layout);
    }

    public void bind(PokemonDetails pokemonDetails) {
        //Abilities
        for(Abilities abilities : pokemonDetails.getAbilities()) {
            addAbilityToLayout(abilitiesLayout, abilities);
        }
    }

    private void addAbilityToLayout(LinearLayout abilitiesLayout, Abilities abilities) {
        RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(10,0,0,10);
        RelativeLayout relativeLayout = new RelativeLayout(view.getContext());
        relativeLayout.setLayoutParams(lparams);

        if(abilities.isIs_hidden()){
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
        String name = abilities.getAbility().getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        textView.setText(name);

        relativeLayout.addView(textView);

        abilitiesLayout.addView(relativeLayout);
    }
}
