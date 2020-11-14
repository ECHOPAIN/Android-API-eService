package com.example.android_api_eservice.presentation.pokemon.pokedex.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.android_api_eservice.PokemonDetail;
import com.example.android_api_eservice.R;

import java.util.ArrayList;
import java.util.List;

public class PokemonGridAdapter extends RecyclerView.Adapter<PokemonGridAdapter.PokemonViewHolder> {
private List<PokemonViewItem> pokemonViewItemList;
private PokemonActionInterface pokemonActionInterface;


// Provide a suitable constructor
public PokemonGridAdapter(PokemonActionInterface pokemonActionInterface) {
    pokemonViewItemList = new ArrayList<>();
    this.pokemonActionInterface = pokemonActionInterface;
}

public void bindViewModels(List<PokemonViewItem> pokemonViewItem) {
    this.pokemonViewItemList.clear();
    this.pokemonViewItemList.addAll(pokemonViewItem);
    notifyDataSetChanged();
}

// Create new views
@Override
public PokemonViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
    // create a new view
    View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.pokedex_grid_item, parent, false);
    PokemonViewHolder pokemonViewHolder = new PokemonViewHolder(v, pokemonActionInterface);
    return pokemonViewHolder;
}

// Replace the contents of a view
@Override
public void onBindViewHolder(PokemonViewHolder holder, final int position) {
    holder.bind(pokemonViewItemList.get(position));

    //open PokemonDetail activity
    holder.parentLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Toast.makeText(view.getContext(), pokemonViewItemList.get(position).getName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(view.getContext(), PokemonDetail.class);
            intent.putExtra("pokemonId",pokemonViewItemList.get(position).getId());
            view.getContext().startActivity(intent);
        }
    });
}

// Return the size of your dataset
@Override
public int getItemCount() {
    return pokemonViewItemList.size();
}


public static class PokemonViewHolder extends RecyclerView.ViewHolder {
    private TextView pokemonId;
    private TextView pokemonName;
    private ImageView image;
    private View v;
    private CheckBox favoriteCheckBox;
    private PokemonActionInterface pokemonActionInterface;
    private PokemonViewItem pokemonViewItem;
    CardView parentLayout;

    public PokemonViewHolder(View v, final PokemonActionInterface pokemonActionInterface) {
        super(v);
        this.v = v;
        pokemonId = v.findViewById((R.id.pokemon_id));
        pokemonName = v.findViewById(R.id.pokemon_name);
        image = v.findViewById(R.id.image);
        parentLayout = itemView.findViewById(R.id.parent_layout);
        favoriteCheckBox = v.findViewById(R.id.favorite_checkbox);
        this.pokemonActionInterface = pokemonActionInterface;
        setupListeners();
    }

    private void setupListeners() {
        favoriteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                pokemonActionInterface.onFavoriteToggle(pokemonViewItem.getId(), b);
                pokemonViewItem.setFavorite(b);
            }
        });
    }


    void bind(PokemonViewItem pokemonViewItem) {
        this.pokemonViewItem = pokemonViewItem;

        //Id
        pokemonId.setText("#" + pokemonViewItem.getId());

        //Name
        String name = pokemonViewItem.getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        pokemonName.setText(name);
        pokemonName.setText(name);

        //Image
        Glide.with(v)
                .load(pokemonViewItem.getFront_default())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .circleCrop()
                .into(image);

        //Favorite CheckBox
        favoriteCheckBox.setChecked(pokemonViewItem.isFavorite());
    }

}
}
