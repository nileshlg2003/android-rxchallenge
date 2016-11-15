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
import com.nilesh.nyuyutest.network.Apicall;
import com.nilesh.nyuyutest.network.Film;
import com.nilesh.nyuyutest.network.RecyclerShip;
import com.nilesh.nyuyutest.network.StarWarsAPI;
import com.nilesh.nyuyutest.network.StarWarsClient;
import com.nilesh.nyuyutest.network.Starships;
import com.nilesh.nyuyutest.network.finalOutput;
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

  private StarWarsAdapter mAdapter;

  private final Map<String, String> mAllFilmURLsMap = new HashMap<>();
  private final List<Starships> mAllShips = new ArrayList<>();
  private final List<String> mApiCallLog = new ArrayList<>();
  private ArrayList <RecyclerShip> mAllRecyclerShips = new ArrayList<>();



  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    mContext = getApplicationContext();
    setupViews();

    recyclerView.setLayoutManager(new LinearLayoutManager(this));


    //Log.d(TAG, "onCreate: " + savedInstanceState.containsKey("key"));
    if (savedInstanceState != null && savedInstanceState.containsKey("key")) {
      Log.d(TAG, "onCreate: coming ere");
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
     // mAllRecyclerShips = savedInstanceState.getParcelableArrayList("key");
      //categories =  getArguments().getParcelableArrayList("list_categories");
      mAllRecyclerShips = savedInstanceState.getParcelableArrayList("key");
      Log.d(TAG, "onCreate: " + mAllRecyclerShips);
      assert mAllRecyclerShips != null;
      mAdapter = new StarWarsAdapter(mContext, mAllRecyclerShips.subList(0,15));
      recyclerView.setAdapter(starWarsAdapter);
    }else {

      downloadData();
    }

  }

  private void setupViews() {
    progressBar = (ProgressBar) findViewById(R.id.rx_progress_bar);
    recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
  }

  private void setupRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    //starWarsAdapter = new StarWarsAdapter(new ArrayList<Starships>());
    //starWarsAdapter = new StarWarsAdapter(mContext, new ArrayList<RecyclerShip>());

  }

  private void downloadData() {
    Log.d(TAG, "downloadData ");
    showProgressBar();

    new Apicall(StarWarsClient.getRxApi())

        .getAllStarships(1)
        .concatMap(new Func1<finalOutput, Observable<Starships>>() {
          @Override
          public Observable<Starships> call(finalOutput result) {
            return Observable.from(result.getShips());
          }
        })

        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

        .subscribe(new Subscriber<Starships>()  {
          @Override
          public void onCompleted() {
            mAllRecyclerShips = StarshipComparator.populateRecyclerShipList(mAllShips, mAllFilmURLsMap, mAllRecyclerShips);
            mAllRecyclerShips = StarshipComparator.sortList(mAllRecyclerShips);
            Log.d(TAG, "onCompleted: " + mAllRecyclerShips);
            recyclerView.setVisibility(View.VISIBLE);
            hideProgressBar();
            mAdapter = new StarWarsAdapter(mContext, mAllRecyclerShips.subList(0,15));
            recyclerView.setAdapter(mAdapter);
          }

          @Override
          public void onError(Throwable e) {
            Log.e(TAG, "onError " + e.getMessage());
            e.printStackTrace();
            hideProgressBar();
            Toast.makeText(MainActivity.this, "Cannot download data", Toast.LENGTH_SHORT).show();
          }



          @Override public void onNext(Starships starshipses) {

            for (String film: starshipses.getFilms()) {


              if (!mAllFilmURLsMap.containsKey(film) && !mApiCallLog.contains(film)) {
                getFilmAndSave(StarWarsClient.getRxApi(), film);
                mApiCallLog.add(film);
              }
            }
           mAllShips.add(starshipses);
            //starWarsAdapter.dataSet.add( mAllShips);

          }

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

  protected void onSaveInstanceState(Bundle outState) {
    if (recyclerView.getAdapter() != null)
      outState.putParcelableArrayList("key", mAllRecyclerShips);
    super.onSaveInstanceState(outState);
  }
}
