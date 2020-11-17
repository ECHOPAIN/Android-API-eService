package com.example.android_api_eservice.presentation.pokemon.favorite.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.android_api_eservice.presentation.pokemondetail.PokemonDetailActivity;
import com.example.android_api_eservice.R;
import java.util.ArrayList;
import java.util.List;

public class PokemonDetailAdapter extends RecyclerView.Adapter<PokemonDetailAdapter.PokemonDetailViewHolder>{
    private List<PokemonDetailViewModel> pokemonDetailViewModelList;
    private PokemonDetailActionInterface pokemonDetailActionInterface;

    // Provide a suitable constructor
    public PokemonDetailAdapter(PokemonDetailActionInterface pokemonDetailActionInterface) {
        pokemonDetailViewModelList = new ArrayList<>();
        this.pokemonDetailActionInterface = pokemonDetailActionInterface;
    }

    public void bindViewModels(List<PokemonDetailViewModel> pokemonDetailViewModelList){
        this.pokemonDetailViewModelList.clear();
        this.pokemonDetailViewModelList.addAll(pokemonDetailViewModelList);
        notifyDataSetChanged();
    }

    // Create new views
    @NonNull
    @Override
    public PokemonDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_favorit_list_item, parent, false);
        PokemonDetailViewHolder pokemonDetailViewHolder = new PokemonDetailViewHolder(v, pokemonDetailActionInterface);
        return pokemonDetailViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonDetailViewHolder holder, final int position) {
        holder.bind(pokemonDetailViewModelList.get(position));

        //open PokemonDetail activity
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PokemonDetailActivity.class);
                intent.putExtra("pokemonId",pokemonDetailViewModelList.get(position).getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return pokemonDetailViewModelList.size();
    }



    public static class PokemonDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView pokemonId;
        private TextView imageName;
        private TextView pokemonType;
        private ImageView image;
        private TextView pokemonAtk;
        private TextView pokemonDef;
        private TextView pokemonSAtk;
        private TextView pokemonSDef;
        private TextView pokemonHp;
        private TextView pokemonSpd;
        private TextView pokemonHeight;
        private TextView pokemonWeight;
        private CardView cardView;
        private View v;
        private PokemonDetailViewModel pokemonDetailViewModel;
        private ImageButton favoriteTrash;
        private PokemonDetailActionInterface pokemonDetailActionInterface;

        public PokemonDetailViewHolder(View v, final PokemonDetailActionInterface pokemonDetailActionInterface) {
            super(v);
            this.v = v;
            this.pokemonDetailActionInterface = pokemonDetailActionInterface;
            cardView = v.findViewById(R.id.parent_layout);
            pokemonId = v.findViewById(R.id.pokemon_id);
            imageName = v.findViewById(R.id.pokemon_name);
            pokemonType = v.findViewById(R.id.pokemon_type);
            image = v.findViewById(R.id.image);
            pokemonAtk = v.findViewById(R.id.pokemon_attack_value);
            pokemonDef = v.findViewById(R.id.pokemon_defense_value);
            pokemonSAtk = v.findViewById(R.id.pokemon_spatk_value);
            pokemonSDef = v.findViewById(R.id.pokemon_spdef_value);
            pokemonHp = v.findViewById(R.id.pokemon_hp_value);
            pokemonSpd = v.findViewById(R.id.pokemon_speed_value);
            pokemonHeight = v.findViewById(R.id.pokemon_height_value);
            pokemonWeight = v.findViewById(R.id.pokemon_weight_value);
            favoriteTrash = v.findViewById(R.id.favorite_trash);
            setupListeners();
        }

        private void setupListeners() {
            favoriteTrash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pokemonDetailActionInterface.onRemoveFavorite(pokemonDetailViewModel.getId());
                }
            });
        }

        void bind(PokemonDetailViewModel pokemonDetailViewModel) {
            this.pokemonDetailViewModel = pokemonDetailViewModel;

            //Id
            pokemonId.setText("#"+pokemonDetailViewModel.getId());

            //Name
            String name = pokemonDetailViewModel.getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            imageName.setText(name);

            //Types
            if(pokemonDetailViewModel.getSecondaryType()==null){
                pokemonType.setText(pokemonDetailViewModel.getPrimaryType().toUpperCase());
            }else{
                pokemonType.setText(pokemonDetailViewModel.getPrimaryType().toUpperCase() + " - " + pokemonDetailViewModel.getSecondaryType().toUpperCase());
            }
            int color = getColorFromType(v.getContext(),pokemonDetailViewModel.getPrimaryType());
            int transparency = 0XAA000000;
            cardView.setCardBackgroundColor(color+transparency);

            //Image
            Glide.with(v)
                    .load(pokemonDetailViewModel.getFront_default())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(image);

            //Stats
            pokemonAtk.setText(pokemonDetailViewModel.getAttackValue());
            pokemonDef.setText(pokemonDetailViewModel.getDefenseValue());
            pokemonSAtk.setText(pokemonDetailViewModel.getSpecialAttackValue());
            pokemonSDef.setText(pokemonDetailViewModel.getSpecialDefenseValue());
            pokemonHp.setText(pokemonDetailViewModel.getHpValue());
            pokemonSpd.setText(pokemonDetailViewModel.getSpeedValue());

            //Height
            String height = pokemonDetailViewModel.getHeight();
            if(height.length()<=1){
                height=0+height;
            }
            height=height.substring(0,height.length()-1)+"."+height.substring(height.length()-1,height.length());
            pokemonHeight.setText(height+"m");

            //Weight
            String weight = pokemonDetailViewModel.getWeight();
            if(weight.length()<=1){
                weight=0+weight;
            }
            weight=weight.substring(0,weight.length()-1)+"."+weight.substring(weight.length()-1,weight.length());
            pokemonWeight.setText(weight+"kg");
        }


        private int getColorFromType(Context context, String primaryType) {
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
}
