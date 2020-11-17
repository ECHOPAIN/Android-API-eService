package com.example.android_api_eservice.presentation.pokemon;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.android_api_eservice.R;
import com.example.android_api_eservice.presentation.pokemon.favorite.fragment.FavoriteFragment;
import com.example.android_api_eservice.presentation.pokemon.pokedex.fragment.PokemonFragment;

public class PokemonActivity extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPagerAndTabs();
    }

    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.view_pager);
        final PokemonFragment pokemonFragment = new PokemonFragment();
        final FavoriteFragment favoriteFragment = new FavoriteFragment();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return pokemonFragment;
                }
                return favoriteFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return PokemonFragment.TAB_NAME;
                }
                return FavoriteFragment.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }

}