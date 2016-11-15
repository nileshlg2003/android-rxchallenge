package com.nilesh.nyuyutest;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nilesh.nyuyutest.network.Starships;
import java.util.ArrayList;

/**
 * Created by apple on 11/15/16.
 */

public class StarWarsAdapter extends RecyclerView.Adapter<StarWarsAdapter.ItemViewHolder> {

  public static final String TAG = StarWarsAdapter.class.getSimpleName();

  public ArrayList<Starships> dataSet = new ArrayList<>();

  public StarWarsAdapter(ArrayList<Starships> dataSet) {
    this.dataSet = dataSet;
  }

  @Override
  public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Log.d(TAG, "onCreateViewHolder ");
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.starships_item, parent, false);
    return new ItemViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ItemViewHolder holder, final int position) {
    Log.d(TAG, "onBindViewHolder ");
    Starships item = dataSet.get(position);
    //bind views here
    // holder.name.setText(item.getName());
    holder.name.setText(item.getName());
    // holder.mass.setText(item.getMass() + " kg");
    // Log.i(TAG, "onBindViewHolder: "+ item.getCostInCredits());
    holder.cost.setText(item.getCostInCredits());
    //item.withCostInCredits()
    //holder.height.setText(item.getHeight() + " m");
    holder.films.setText(item.getLength() );
  }

  @Override
  public int getItemCount() {
    return dataSet.size();
  }

  public static class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView cost;
    public TextView films;

    public ItemViewHolder(View view) {
      super(view);
      view = itemView;
      name = (TextView) view.findViewById(R.id.name);
      cost = (TextView) view.findViewById(R.id.cost);
      films = (TextView) view.findViewById(R.id.films);
    }
  }
}
