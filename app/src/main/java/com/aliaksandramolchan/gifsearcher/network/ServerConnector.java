package com.aliaksandramolchan.gifsearcher.network;


import android.app.Application;

import com.aliaksandramolchan.gifsearcher.extra.Extras;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerConnector extends Application {
    private static GiphyApi giphyApi;

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Extras.ENDPOINT_GIPHY)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()
                );
        Retrofit retrofit = builder.client(httpClient.build())
                .build();
        giphyApi = retrofit.create(GiphyApi.class);
    }

    public static GiphyApi getGiphyApi() {
        return giphyApi;
    }

}
