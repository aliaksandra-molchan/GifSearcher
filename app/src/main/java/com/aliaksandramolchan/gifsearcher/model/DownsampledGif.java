package com.aliaksandramolchan.gifsearcher.model;

import com.google.gson.annotations.SerializedName;

public class DownsampledGif {
    @SerializedName("url")
    private String url;

    public DownsampledGif(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
    
}
