package com.aliaksandramolchan.gifsearcher.view;


import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BindingsAdapter {
    @BindingAdapter("onLayoutChangeListener")
    public static void setOnLayoutChangeListener(
            ImageView imageView, ImageView.OnLayoutChangeListener onLayoutChangeListener) {
        imageView.addOnLayoutChangeListener(onLayoutChangeListener);
    }

    @BindingAdapter("gifUrl")
    public static void loadGif(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .asGif()
                .override(300, 300)
                .into(imageView);
    }
}
