package com.nilesh.nyuyutest.network;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 11/16/16.
 */

public class Apicall {

  private final StarWarsAPI mApiInterface;

  public Apicall(StarWarsAPI apiInterface) {
    mApiInterface = apiInterface;
  }


  public Observable<finalOutput> getAllStarships(final int page) {
    return mApiInterface.getRxStarshipsByID(page)
        .concatMap(new Func1<finalOutput, Observable<finalOutput>>() {

          @Override
          public Observable<finalOutput> call(finalOutput finalOutput) {
            if (finalOutput.getNext() == null) {
              return Observable.just(finalOutput);
            }
            return Observable.just(finalOutput).concatWith(getAllStarships(page + 1));
          }
        })
        .subscribeOn(Schedulers.io())


        .observeOn(AndroidSchedulers.mainThread());
  }
}