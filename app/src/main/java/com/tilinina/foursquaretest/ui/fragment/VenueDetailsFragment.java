package com.tilinina.foursquaretest.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tilinina.foursquaretest.R;


public class VenueDetailsFragment extends Fragment
{

  public static final String TAG = "VenueDetailsFragment";


  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    // Initialize data
    initDataset();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState)
  {
    View rootView = inflater.inflate(R.layout.venue_details_fragment_layout, container, false);
    rootView.setTag(TAG);

    if (savedInstanceState != null)
    {
      //TODO Restore saved
    }

    return rootView;
  }

  @Override
  public void onSaveInstanceState(Bundle savedInstanceState)
  {
    //TODO save data.
    //savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
    super.onSaveInstanceState(savedInstanceState);
  }

  private void initDataset()
  {

  }
}
