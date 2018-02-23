package com.aliaksandramolchan.gifsearcher.viewmodel;


import android.support.v7.widget.RecyclerView;

import com.aliaksandramolchan.gifsearcher.extra.Extras;
import com.aliaksandramolchan.gifsearcher.model.DownsampledGif;
import com.aliaksandramolchan.gifsearcher.model.Gif;
import com.aliaksandramolchan.gifsearcher.network.ServerConnector;
import com.aliaksandramolchan.gifsearcher.model.Images;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GifViewModel extends Observable {

    private List<Gif> gifsList = new ArrayList<>();

    private Disposable trendingDisp;

    public RecyclerView.OnScrollListener getOnScrollListener(){
        return new RecyclerView.OnScrollListener(){
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                recyclerView.invalidateItemDecorations();
            }
        }
    };
    }
    public void fetchTrendingGifs() {
        trendingDisp = ServerConnector.getGiphyApi().getTrendingGifs(Extras.API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(gifResponse -> {
                            gifsList=gifResponse.getGifs();
                            setChanges();
                        },
                        error -> {
                            error.printStackTrace();
                        }
                );
    }

    public void setGifsUrls(ArrayList<String> urls) {
        for (String url : urls) {
            gifsList.add(new Gif(new Images(new DownsampledGif(url))));
        }
        setChanges();
    }

    public List<Gif> getGifsList() {
        return gifsList;
    }

    public void reset() {
        if (trendingDisp != null) {
            trendingDisp.dispose();
        }
    }

    private void setChanges(){
        setChanged();
        notifyObservers();
    }

}
