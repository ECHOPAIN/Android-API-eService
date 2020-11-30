package com.example.android_api_eservice.presentation.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.example.android_api_eservice.R;

public class Util {

    public static int getBackgroundFromType(String type) {
        switch(type.toLowerCase()) {
            case "normal":
                return R.drawable.normal;
            case "fighting":
                return R.drawable.fighting;
            case "poison":
                return R.drawable.poison;
            case "ground":
                return R.drawable.ground;
            case "rock":
                return R.drawable.rock;
            case "bug":
                return R.drawable.bug;
            case "ghost":
                return R.drawable.ghost;
            case "steel":
                return R.drawable.steel;
            case "fire":
                return R.drawable.fire;
            case "water":
                return R.drawable.water;
            case "grass":
                return R.drawable.grass;
            case "electric":
                return R.drawable.electric;
            case "psychic":
                return R.drawable.psychic;
            case "ice":
                return R.drawable.ice;
            case "dragon":
                return R.drawable.dragon;
            case "dark":
                return R.drawable.dark;
            case "fairy":
                return R.drawable.fairy;
            case "fly":
                return R.drawable.fly;

            default:
                return R.drawable.normal;
        }
    }

    public static int getColorFromType(Context context, String primaryType) {
        switch(primaryType.toLowerCase()) {
            case "normal":
                return  ContextCompat.getColor(context,R.color.normal);
            case "fighting":
                return ContextCompat.getColor(context,R.color.fighting);
            case "poison":
                return ContextCompat.getColor(context,R.color.poison);
            case "ground":
                return ContextCompat.getColor(context,R.color.ground);
            case "rock":
                return ContextCompat.getColor(context,R.color.rock);
            case "bug":
                return ContextCompat.getColor(context,R.color.bug);
            case "ghost":
                return ContextCompat.getColor(context,R.color.ghost);
            case "steel":
                return ContextCompat.getColor(context,R.color.steel);
            case "fire":
                return ContextCompat.getColor(context,R.color.fire);
            case "water":
                return ContextCompat.getColor(context,R.color.water);
            case "grass":
                return ContextCompat.getColor(context,R.color.grass);
            case "electric":
                return ContextCompat.getColor(context,R.color.electric);
            case "psychic":
                return ContextCompat.getColor(context,R.color.psychic);
            case "ice":
                return ContextCompat.getColor(context,R.color.ice);
            case "dragon":
                return ContextCompat.getColor(context,R.color.dragon);
            case "dark":
                return ContextCompat.getColor(context,R.color.dark);
            case "fairy":
                return ContextCompat.getColor(context,R.color.fairy);
            case "flying":
                return ContextCompat.getColor(context,R.color.flying);

            default:
                return ContextCompat.getColor(context,R.color.normal);
        }
    }
}
