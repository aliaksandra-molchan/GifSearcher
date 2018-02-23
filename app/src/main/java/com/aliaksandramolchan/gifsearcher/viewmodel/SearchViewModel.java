package com.aliaksandramolchan.gifsearcher.viewmodel;


import android.widget.SearchView;

import com.aliaksandramolchan.gifsearcher.extra.Extras;
import com.aliaksandramolchan.gifsearcher.network.ServerConnector;
import com.aliaksandramolchan.gifsearcher.model.Gif;

import java.util.ArrayList;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends Observable {
    private ArrayList<String> gifsUrl = new ArrayList<>();

    private Disposable searchDisp;

    public ArrayList<String> getGifsUrl() {
        return gifsUrl;
    }

    public SearchView.OnQueryTextListener getOnQueryTextListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchSearchedGifs(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };
    }

    private void fetchSearchedGifs(String searchText) {
        searchDisp = ServerConnector.getGiphyApi().searchGifs(
                Extras.API_KEY, convertSearchText(searchText))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(gifResponse -> {
                            if (!gifsUrl.isEmpty()) {
                                gifsUrl.clear();
                            }
                            for (Gif gif : gifResponse.getGifs()) {
                                gifsUrl.add(gif.getImages().getDownsampledGif().getUrl());
                            }
                            setChanged();
                            notifyObservers();
                        },
                        error -> {
                            error.printStackTrace();
                        }
                );
    }

    private String convertSearchText(String text) {
        return text.replace(' ', '+');
    }

    public void reset() {
        if (searchDisp != null) {
            searchDisp.dispose();
        }
    }

}
