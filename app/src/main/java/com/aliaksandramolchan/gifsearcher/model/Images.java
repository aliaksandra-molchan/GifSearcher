package com.aliaksandramolchan.gifsearcher.model;


import com.google.gson.annotations.SerializedName;

public class Images {
    @SerializedName("fixed_height")
    private DownsampledGif downsampledGif;

    public Images(DownsampledGif downsampledGif) {
        this.downsampledGif = downsampledGif;
    }

    public DownsampledGif getDownsampledGif() {
        return downsampledGif;
    }

}
