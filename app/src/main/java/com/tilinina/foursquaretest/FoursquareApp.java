package com.tilinina.foursquaretest;


import android.app.Application;

import com.tilinina.foursquaretest.retrofit.api.VenuesApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoursquareApp extends Application
{
  public final String baseVenuesUrl = "https://api.foursquare.com/";
  public final static String CLIENT_ID = "NX1DRVT5FPXO1YOC0530352DAOKG0XDHSMT3DDEWN3TBNHXD";
  public final static String CLIENT_SECRET = "OKLVSBICLJLAZN2KCZXW1ALPIRPEQ2BWNR2Y23ALSTQ1EJBY";
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
