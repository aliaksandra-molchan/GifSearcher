package com.aliaksandramolchan.gifsearcher.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GifResponse {
    @SerializedName("data")
    List<Gif> gifs;

    public List<Gif> getGifs() {
        return gifs;
    }
}
