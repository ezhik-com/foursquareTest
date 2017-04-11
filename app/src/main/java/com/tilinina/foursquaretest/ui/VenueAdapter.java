package com.tilinina.foursquaretest.ui;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tilinina.foursquaretest.R;
import com.tilinina.foursquaretest.model.Venue;

import java.util.List;

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.ViewHolder> {

  private List<Venue> venues;

  public VenueAdapter(List<Venue> venues) {
    this.venues = venues;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.venues_list_item, parent, false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    Venue post = venues.get(position);
    holder.post.setText(post.getName());
    holder.site.setText(post.getId());
  }

  @Override
  public int getItemCount() {
    if (venues == null)
      return 0;
    return venues.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    TextView post;
    TextView site;

    public ViewHolder(View itemView) {
      super(itemView);
      post = (TextView) itemView.findViewById(R.id.postitem_post);
      site = (TextView) itemView.findViewById(R.id.postitem_site);
    }
  }
}