package com.tilinina.foursquaretest;


import android.app.Application;

import com.tilinina.foursquaretest.retrofit.api.VenuesApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoursquareApp extends Application
{
  public final String baseVenuesUrl = "https://api.foursquare.com/v2/venues/";
  private static VenuesApi venuesApi;
  private Retrofit retrofit;

  public FoursquareApp()
  {
    retrofit = new Retrofit.Builder()
            .baseUrl(baseVenuesUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    venuesApi = retrofit.create(VenuesApi.class);
  }

  public static VenuesApi getVenuesApi()
  {
    return venuesApi;
  }
}
