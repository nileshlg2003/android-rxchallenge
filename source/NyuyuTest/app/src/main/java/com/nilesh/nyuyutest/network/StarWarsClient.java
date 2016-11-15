package com.nilesh.nyuyutest.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by apple on 11/15/16.
 */

public class StarWarsClient {

  private static Retrofit buildRetrofit() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(StarWarsAPI.ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    return retrofit;
  }
  private static Retrofit buildRxRetrofit() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(StarWarsAPI.ENDPOINT)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    return retrofit;
  }

  public static StarWarsAPI getApi() {
    return buildRetrofit().create(StarWarsAPI.class);
  }
  public static StarWarsAPI getRxApi() {
    return buildRxRetrofit().create(StarWarsAPI.class);
  }

  
}
