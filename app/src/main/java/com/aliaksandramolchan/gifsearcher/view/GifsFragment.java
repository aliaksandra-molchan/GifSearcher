package com.aliaksandramolchan.gifsearcher.view;


import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliaksandramolchan.gifsearcher.extra.Extras;
import com.aliaksandramolchan.gifsearcher.R;
import com.aliaksandramolchan.gifsearcher.databinding.FragmentGifBinding;
import com.aliaksandramolchan.gifsearcher.viewmodel.GifViewModel;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class GifsFragment extends Fragment implements Observer {
    private FragmentGifBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gif, container, false);
        binding.setGifViewModel(new GifViewModel());
        binding.getGifViewModel().addObserver(this);
        if (getArguments() == null) {
            binding.getGifViewModel().fetchTrendingGifs();
        }
        initRecyclerView();
        return binding.getRoot();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = binding.gifRecyclerView;
        StaggeredGridLayoutManager lm = new StaggeredGridLayoutManager(
                getColumnsCount(), StaggeredGridLayoutManager.VERTICAL);
        lm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(new GifsAdapter());
        recyclerView.addOnScrollListener(binding.getGifViewModel().getOnScrollListener());
    }

    private int getColumnsCount() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            ArrayList<String> urls = getArguments().getStringArrayList(Extras.SEARCHED_KEY);
            if (urls != null) {
                binding.getGifViewModel().setGifsUrls(urls);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.getGifViewModel().reset();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GifViewModel) {
            GifViewModel gifViewModel = (GifViewModel) o;
            GifsAdapter adapter = (GifsAdapter) binding.gifRecyclerView.getAdapter();
            adapter.setGifs(gifViewModel.getGifsList());
        }
    }
}
