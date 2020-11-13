package com.example.android_api_eservice.presentation.pokemon.favorite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.android_api_eservice.R;
import com.example.android_api_eservice.presentation.pokemon.pokedex.adapter.PokemonActionInterface;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetailAdapter extends RecyclerView.Adapter<PokemonDetailAdapter.PokemonDetailViewHolder>{


    private List<PokemonDetailViewModel> pokemonDetailViewModelList;
    private PokemonDetailActionInterface pokemonDetailActionInterface;
    public PokemonDetailAdapter(PokemonDetailActionInterface pokemonDetailActionInterface) {
        pokemonDetailViewModelList = new ArrayList<>();
        this.pokemonDetailActionInterface = pokemonDetailActionInterface;
    }


    public void bindViewModels(List<PokemonDetailViewModel> pokemonDetailViewModelList){
        this.pokemonDetailViewModelList.clear();
        this.pokemonDetailViewModelList.addAll(pokemonDetailViewModelList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokemonDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_detailed_listitem, parent, false);
        PokemonDetailViewHolder pokemonDetailViewHolder = new PokemonDetailViewHolder(v, pokemonDetailActionInterface);
        return pokemonDetailViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonDetailViewHolder holder, int position) {
        holder.bind(pokemonDetailViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemonDetailViewModelList.size();
    }


    public static class PokemonDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView imageName;
        private ImageView image;
        private View v;
        private PokemonDetailViewModel pokemonDetailViewModel;
        private CheckBox favoriteCheckBox;
        private PokemonDetailActionInterface pokemonDetailActionInterface;


        public PokemonDetailViewHolder(View v, final PokemonDetailActionInterface pokemonDetailActionInterface) {
            super(v);
            this.v = v;
            imageName = v.findViewById(R.id.image_name);
            image = v.findViewById(R.id.image);
            favoriteCheckBox = v.findViewById(R.id.favorite_checkbox);
            setupListeners();
            this.pokemonDetailActionInterface = pokemonDetailActionInterface;
        }

        private void setupListeners() {
            favoriteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (!b) {
                        pokemonDetailActionInterface.onRemoveFavorite(pokemonDetailViewModel.getId());
                    }
                }
            });
        }



        void bind(PokemonDetailViewModel pokemonDetailViewModel) {
            this.pokemonDetailViewModel = pokemonDetailViewModel;
            imageName.setText("#" + pokemonDetailViewModel.getId() + " " + pokemonDetailViewModel.getName());
            Glide.with(v)
                    .load(pokemonDetailViewModel.getFront_default())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(image);

            favoriteCheckBox.setChecked(true);
        }

    }
}
