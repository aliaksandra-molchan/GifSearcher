package com.aliaksandramolchan.gifsearcher.model;

import com.google.gson.annotations.SerializedName;

public class Gif {
    @SerializedName("images")
    private Images images;

    public Gif(Images images) {
        this.images = images;
    }

    public Images getImages() {
        return images;
    }

}
