package com.tilinina.foursquaretest.ui.fragment;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.tilinina.foursquaretest.FoursquareApp;
import com.tilinina.foursquaretest.R;
import com.tilinina.foursquaretest.model.SearchResponse;
import com.tilinina.foursquaretest.model.Venue;
import com.tilinina.foursquaretest.retrofit.api.VenuesApi;
import com.tilinina.foursquaretest.ui.adapter.VenueAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VenueListFragment extends Fragment
{

  private static final String TAG = "VenueListFragment";

  public final static String LOCATION_BUNDLE_KEY = "location";
  public final static String CITY_BUNDLE_KEY = "city";

  protected VenuesApi venuesApi;
  protected RecyclerView mRecyclerView;
  protected ArrayList<Venue> mVenues;

  String location;
  String cityName;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    venuesApi = FoursquareApp.getVenuesApi();
    mVenues = new ArrayList<>();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState)
  {
    View rootView = inflater.inflate(R.layout.venue_list_fragment_layout, container, false);
    rootView.setTag(TAG);

    if (savedInstanceState != null)
    {
      //TODO Restore saved
    }

    mRecyclerView = (RecyclerView)rootView.findViewById(R.id.venues_recycle_view);
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(layoutManager);

    updateListData();
    return rootView;

  }

  @Override
  public void onSaveInstanceState(Bundle savedInstanceState)
  {
    //TODO Save state.
    //savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
    super.onSaveInstanceState(savedInstanceState);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    Bundle bundle = getArguments();
    if (bundle != null)
    {
      location = bundle.getString(LOCATION_BUNDLE_KEY, "");
      cityName = bundle.getString(CITY_BUNDLE_KEY, "");
    }

    initVenuesList();
  }

  //TODO add onAttach/onDetach for click listener

  private void updateListData()
  {
    if (mRecyclerView.getAdapter()==null)
    {
      VenueAdapter adapter = new VenueAdapter(getActivity(), mVenues);
      mRecyclerView.setAdapter(adapter);
    }
    else
    {
      ((VenueAdapter)mRecyclerView.getAdapter()).updateData(mVenues);
    }

  }

  private void initVenuesList()
  {
    //TODO add caching and get cache if no connection

    Map<String, String> options = new HashMap<>();

    //TODO move to separate func
    options.put("client_id", FoursquareApp.CLIENT_ID);
    options.put("client_secret", FoursquareApp.CLIENT_SECRET);
    options.put("m", "foursquare");
    options.put("v", "20170415");

    options.put("ll", location);

    //TODO add details for best result
    //options.put("city", "20");
//    options.put("limit", "20");
//    options.put("radius", "50000");

    venuesApi.getSearch(options).enqueue(new Callback<SearchResponse>()
    {
      @Override
      public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response)
      {

        if (response.body() != null)
        {
          SearchResponse searchResponse = (SearchResponse)response.body();
          mVenues.clear();
          mVenues.addAll(searchResponse.getResponseBody().getVenues());

          Toast.makeText(
                  getActivity(),
                  "Retrofit Succes: ", Toast.LENGTH_SHORT).show();

          getActivity().runOnUiThread(new Runnable()
          {
            @Override
            public void run()
            {
              updateListData();
            }
          });

        }
      }

      @Override
      public void onFailure(Call<SearchResponse> call, Throwable t)
      {
        Toast.makeText(
                getActivity(),
                "Retrofit Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });
  }
}
