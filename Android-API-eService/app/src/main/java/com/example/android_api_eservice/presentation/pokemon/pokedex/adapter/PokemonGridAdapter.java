package com.example.android_api_eservice.presentation.pokemon.pokedex.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.android_api_eservice.presentation.pokemondetail.PokemonDetailActivity;
import com.example.android_api_eservice.R;
import java.util.ArrayList;
import java.util.List;

public class PokemonGridAdapter extends RecyclerView.Adapter<PokemonGridAdapter.PokemonViewHolder> {
    private List<PokemonViewModel> pokemonViewModelList;
    private PokemonActionInterface pokemonActionInterface;

    // Provide a suitable constructor
    public PokemonGridAdapter(PokemonActionInterface pokemonActionInterface) {
        pokemonViewModelList = new ArrayList<>();
        this.pokemonActionInterface = pokemonActionInterface;
    }

    public void bindViewModels(List<PokemonViewModel> pokemonViewModel) {
        this.pokemonViewModelList.clear();
        this.pokemonViewModelList.addAll(pokemonViewModel);
        notifyDataSetChanged();
    }

    // Create new views
    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokedex_grid_item, parent, false);
        PokemonViewHolder pokemonViewHolder = new PokemonViewHolder(v, pokemonActionInterface);
        return pokemonViewHolder;
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(PokemonViewHolder holder, final int position) {
        holder.bind(pokemonViewModelList.get(position));

        //open PokemonDetail activity
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PokemonDetailActivity.class);
                intent.putExtra("pokemonId", pokemonViewModelList.get(position).getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return pokemonViewModelList.size();
    }



    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        private TextView pokemonId;
        private TextView pokemonName;
        private ImageView image;
        private View v;
        private CheckBox favoriteCheckBox;
        private PokemonActionInterface pokemonActionInterface;
        private PokemonViewModel pokemonViewModel;
        CardView parentLayout;

        public PokemonViewHolder(View v, final PokemonActionInterface pokemonActionInterface) {
            super(v);
            this.v = v;
            this.pokemonActionInterface = pokemonActionInterface;
            pokemonId = v.findViewById((R.id.pokemon_id));
            pokemonName = v.findViewById(R.id.pokemon_name);
            image = v.findViewById(R.id.image);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            favoriteCheckBox = v.findViewById(R.id.favorite_checkbox);
            setupListeners();
        }

        private void setupListeners() {
            favoriteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    pokemonActionInterface.onFavoriteToggle(pokemonViewModel.getId(), b);
                    pokemonViewModel.setFavorite(b);
                }
            });
        }

        void bind(PokemonViewModel pokemonViewModel) {
            this.pokemonViewModel = pokemonViewModel;

            //Id
            pokemonId.setText("#" + pokemonViewModel.getId());

            //Name
            String name = pokemonViewModel.getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            pokemonName.setText(name);
            pokemonName.setText(name);

            //Image
            Glide.with(v)
                    .load(pokemonViewModel.getFront_default())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(image);

            //Favorite CheckBox
            favoriteCheckBox.setChecked(pokemonViewModel.isFavorite());
        }

    }
}
