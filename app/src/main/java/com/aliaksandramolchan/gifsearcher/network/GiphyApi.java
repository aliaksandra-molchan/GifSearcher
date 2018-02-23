package com.aliaksandramolchan.gifsearcher.network;

import com.aliaksandramolchan.gifsearcher.model.GifResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GiphyApi {

    @GET("v1/gifs/trending")
    Observable<GifResponse> getTrendingGifs(
            @Query("api_key") String apiKey);

    @GET("v1/gifs/search")
    Observable<GifResponse> searchGifs(
            @Query("api_key") String apiKey,
            @Query("q") String searchText);
}
