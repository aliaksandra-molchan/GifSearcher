package com.aliaksandramolchan.gifsearcher.view;


import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aliaksandramolchan.gifsearcher.extra.Extras;
import com.aliaksandramolchan.gifsearcher.R;
import com.aliaksandramolchan.gifsearcher.databinding.ActivityMainBinding;
import com.aliaksandramolchan.gifsearcher.viewmodel.SearchViewModel;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    private ActivityMainBinding binding;
    private GifsFragment mainFragment;
    private GifsFragment searchFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setSearchViewModel(new SearchViewModel());
        binding.getSearchViewModel().addObserver(this);
        binding.search.setOnQueryTextListener(
                binding.getSearchViewModel().getOnQueryTextListener());
        mainFragment = new GifsFragment();
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.activity_main, mainFragment, Extras.MAIN_FRAGMENT)
                    .commit();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.getSearchViewModel().reset();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof SearchViewModel) {
            searchFragment = new GifsFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.activity_main, searchFragment)
                    .addToBackStack(null)
                    .commit();
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(Extras.SEARCHED_KEY, ((SearchViewModel) o).getGifsUrl());
            searchFragment.setArguments(bundle);

        }
    }

}
