package com.nilesh.nyuyutest.network;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by apple on 11/15/16.
 */

public interface StarWarsAPI {

  String ENDPOINT = "http://swapi.co/api/";

 /* @GET("starships/{id}")
  rx.Observable<Starships> getRxStarshipsByID(@Path("id") int id);*/

  @GET("starships/")
  Observable<finalOutput> getRxStarshipsByID(@Query("page") int page);

  @GET Observable<Film> getFilmByURL(@Url String url);
}
