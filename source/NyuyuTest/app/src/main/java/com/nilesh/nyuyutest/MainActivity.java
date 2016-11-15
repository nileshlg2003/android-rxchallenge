package com.nilesh.nyuyutest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.nilesh.nyuyutest.network.Film;
import com.nilesh.nyuyutest.network.StarWarsAPI;
import com.nilesh.nyuyutest.network.StarWarsClient;
import com.nilesh.nyuyutest.network.Starships;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

  public static final String TAG = MainActivity.class.getSimpleName();

  private Context mContext;

  RecyclerView recyclerView;
  ProgressBar progressBar;
  StarWarsAdapter starWarsAdapter;
  rx.Subscription subscription;

  private final Map<String, String> mAllFilmURLsMap = new HashMap<>();
  private final List<Starships> mAllShips = new ArrayList<>();
  private final List<String> mApiCallLog = new ArrayList<>();



  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    setupViews();
    setupRecyclerView();
    downloadData();

  }

  private void setupViews() {
    progressBar = (ProgressBar) findViewById(R.id.rx_progress_bar);
    recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
  }

  private void setupRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    starWarsAdapter = new StarWarsAdapter(new ArrayList<Starships>());
    recyclerView.setAdapter(starWarsAdapter);
  }

  private void downloadData() {
    Log.d(TAG, "downloadData1 ");
    showProgressBar();

    subscription = Observable.range(1, 10).flatMap(new Func1<Integer, Observable<Starships>>() {
      @Override
      public Observable<Starships> call(Integer characterId) {
        return StarWarsClient.getRxApi().getRxStarshipsByID(characterId).onErrorResumeNext(new Func1<Throwable, Observable<? extends Starships>>() {
          @Override

          public Observable<? extends Starships> call(Throwable throwable) {
            return Observable.empty();
          }
        });
      }
    }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

        //.toSortedList(MainActivity::compareModel)

           /* .toSortedList(new Func2<Starships, Starships, Integer>() {


              @Override
              public Integer call(Starships starships, Starships starships2) {
                Log.d(TAG, "call: " + starships.getCostInCredits());
                return starships.getCostInCredits().compareTo(starships2.getCostInCredits());
              }
            })*/
            /*.toSortedList((starships, starships2) -> {

              return starships.getCostInCredits().compareTo(starships2.getCostInCredits());
              //return null;
            })*/

           /* .subscribe(new Subscriber<List<Starships>>() {
                @Override public void onCompleted() {
                    starWarsAdapter.notifyItemRangeChanged(0, starWarsAdapter.getItemCount());
                    hideProgressBar();
                }

                @Override public void onError(Throwable e) {

                    Log.e(TAG, "onError " + e.getMessage());
                    e.printStackTrace();
                    hideProgressBar();
                    Toast.makeText(RxJavaActivity.this, "Cannot download data", Toast.LENGTH_SHORT).show();

                }

                @Override public void onNext(List<Starships> starshipses) {

                    starWarsAdapter.dataSet.add((Starships) starshipses);
                  //starWarsAdapter.dataSet.add(starshipses);
                }*/


        .subscribe(new Subscriber<Starships>()  {
          @Override
          public void onCompleted() {

            starWarsAdapter.notifyItemRangeChanged(0, starWarsAdapter.getItemCount());
            hideProgressBar();
          }

          @Override
          public void onError(Throwable e) {
            Log.e(TAG, "onError " + e.getMessage());
            e.printStackTrace();
            hideProgressBar();
            Toast.makeText(MainActivity.this, "Cannot download data", Toast.LENGTH_SHORT).show();
          }



          @Override public void onNext(Starships starshipses) {
            starWarsAdapter.dataSet.add((Starships) starshipses);
            for (String film: starshipses.getFilms()) {


              if (!mAllFilmURLsMap.containsKey(film) && !mApiCallLog.contains(film)) {
                getFilmAndSave(StarWarsClient.getRxApi(), film);
                mApiCallLog.add(film);
              }
            }
           mAllShips.add(starshipses);
          }





          /*@Override
            public void onNext(StarWarsCharacter starWarsCharacter) {
                starWarsAdapter.dataSet.add(starWarsCharacter);
            }*/
        });
  }

  private void getFilmAndSave(StarWarsAPI starWarsAPI, String url) {
    starWarsAPI

        .getFilmByURL(url)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Film>() {
          @Override
          public void onCompleted() {

          }

          @Override
          public void onError(Throwable e) {
            Toast.makeText(mContext, e.toString(), Toast.LENGTH_LONG).show();
          }

          @Override
          public void onNext(Film film) {
            //Log.d("MainActivity", film.getTitle());
            mAllFilmURLsMap.put(film.getUrl(), film.getTitle());
            Log.d("MainActivity", mAllFilmURLsMap.toString());
          }
        });
  }

  private static Integer compareModel(Starships starship1, Starships starship2) {
    return starship1.getCostInCredits().compareTo(starship1.getCostInCredits());
  }

  private void showProgressBar() {
    progressBar.setVisibility(View.VISIBLE);
  }

  private void hideProgressBar() {
    progressBar.setVisibility(View.GONE);
  }

  @Override
  protected void onDestroy() {
    if (subscription != null) subscription.unsubscribe();
    super.onDestroy();
  }
}
