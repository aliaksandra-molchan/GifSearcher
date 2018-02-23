package com.aliaksandramolchan.gifsearcher.viewmodel;


import android.databinding.BaseObservable;
import android.databinding.ObservableInt;
import android.view.View;
import android.view.View.OnLayoutChangeListener;

import com.aliaksandramolchan.gifsearcher.model.Gif;

public class GifItemViewModel extends BaseObservable {
    private Gif gif;
    private ObservableInt progressBar;

    public GifItemViewModel() {
        progressBar = new ObservableInt(View.VISIBLE);
    }

    public void setGif(Gif gif) {
        this.gif = gif;
        notifyChange();
    }

    public ObservableInt getProgressBar() {
        return progressBar;
    }

    public OnLayoutChangeListener getOnLayoutChangeListener() {
        return (v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            if (oldBottom < bottom && oldLeft > left
                    && oldRight < right && oldTop > top) {
                progressBar.set(View.INVISIBLE);
            } else if (oldBottom > bottom && oldLeft < left
                    && oldRight > right && oldTop < top) {
                progressBar.set(View.VISIBLE);
            }
        };
    }

    public String getUrl() {
        return gif.getImages().getDownsampledGif().getUrl();
    }

}
