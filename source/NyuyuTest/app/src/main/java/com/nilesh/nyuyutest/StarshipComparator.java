package com.nilesh.nyuyutest;

import android.text.TextUtils;
import com.nilesh.nyuyutest.model.RecyclerShip;
import com.nilesh.nyuyutest.model.Starships;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by apple on 11/16/16.
 */

public class StarshipComparator {


  static ArrayList<RecyclerShip> sortList(ArrayList<RecyclerShip> allRecyclerShips) {
    Collections.sort(allRecyclerShips, new Comparator<RecyclerShip>() {
      @Override
      public int compare(RecyclerShip ship1, RecyclerShip ship2) {
        Long diff = ship1.getCost() - ship2.getCost();
        if(diff < 0)
          return 1;
        else if (diff > 0)
          return -1;
        else
          return 0;
      }
    });
    return allRecyclerShips;
  }

  static ArrayList<RecyclerShip> populateRecyclerShipList(List<Starships> allShips, Map<String, String> allFilmURLsMap, ArrayList<RecyclerShip> allRecyclerShips) {
    Pattern pattern = Pattern.compile("\\d+");
    for (Starships ship: allShips) {
      String shipName = ship.getName();
      String costInString = "Cost: " + ship.getCostInCredits();
      String concatenatedFilmNames = concatenateFilmNames(ship.getFilms(), allFilmURLsMap);
      Long cost;

      if (pattern.matcher(ship.getCostInCredits()).matches())
        cost = Long.parseLong(ship.getCostInCredits());
      else
        cost = 0L;

      if(concatenatedFilmNames.contains(","))
        concatenatedFilmNames = "Films: " + concatenatedFilmNames;
      else
        concatenatedFilmNames = "Film: " + concatenatedFilmNames;

      RecyclerShip tempShip = new RecyclerShip(shipName, cost, costInString, concatenatedFilmNames);
      allRecyclerShips.add(tempShip);
    }
    return allRecyclerShips;
  }

  static private String concatenateFilmNames(List<String> filmURLs, Map<String, String> allFilmURLsMap) {
    List<String> filmNames = new ArrayList<>();
    for (String filmURL: filmURLs) {
      filmNames.add(allFilmURLsMap.get(filmURL));
    }
    return TextUtils.join(", ", filmNames);
  }
}

