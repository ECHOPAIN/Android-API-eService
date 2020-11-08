package com.example.android_api_eservice.presentation.pokemon.pokedex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.android_api_eservice.R;

import java.util.ArrayList;
import java.util.List;

public class PokemonAdapter  extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>{
    private List<PokemonViewItem> pokemonViewItemList;
    private PokemonActionInterface pokemonActionInterface;


    // Provide a suitable constructor
    public PokemonAdapter(PokemonActionInterface pokemonActionInterface) {
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
                .inflate(R.layout.layout_listitem, parent, false);
        PokemonViewHolder pokemonViewHolder = new PokemonViewHolder(v, pokemonActionInterface);
        return pokemonViewHolder;
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(PokemonViewHolder holder, final int position) {
        holder.bind(pokemonViewItemList.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), pokemonViewItemList.get(position).getName(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return pokemonViewItemList.size();
    }






    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        private TextView imageName;
        private ImageView image;
        private View v;
        RelativeLayout parentLayout;

        public PokemonViewHolder(View v, final PokemonActionInterface pokemonActionInterface) {
            super(v);
            this.v = v;
            imageName = v.findViewById(R.id.image_name);
            image = v.findViewById(R.id.image);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }



        void bind(PokemonViewItem pokemonViewItem) {
            imageName.setText("#" + pokemonViewItem.getId() + " " + pokemonViewItem.getName());
            Glide.with(v)
                    .load(pokemonViewItem.getFront_default())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(image);

        }

    }
}
