package com.tilinina.foursquaretest.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tilinina.foursquaretest.R;
import com.tilinina.foursquaretest.model.Venue;

import java.util.List;

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.ViewHolder>
{
  private final TypedValue mTypedValue = new TypedValue();
  private List<Venue> mVenues;
  private int mBackground;

  public VenueAdapter(Context context, List<Venue> venues)
  {
    context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
    mBackground = mTypedValue.resourceId;
    this.mVenues = venues;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
  {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_list_item, parent, false);
    view.setBackgroundResource(mBackground);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position)
  {
    Venue post = mVenues.get(position);
    holder.post.setText(post.getName());
    holder.site.setText(post.getId());

    holder.mView.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
    //TODO start detail fragment
      }
    });
  }

  @Override
  public int getItemCount()
  {
    if (mVenues == null)
      return 0;
    return mVenues.size();
  }

  public void updateData(List<Venue> venues)
  {
    mVenues.clear();
    mVenues.addAll(venues);
    notifyDataSetChanged();
  }

  class ViewHolder extends RecyclerView.ViewHolder
  {
    TextView post;
    TextView site;
    View mView;

    public ViewHolder(View itemView)
    {
      super(itemView);
      mView = itemView;
      post = (TextView)itemView.findViewById(R.id.postitem_post);
      site = (TextView)itemView.findViewById(R.id.postitem_site);
    }
  }
}