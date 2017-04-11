package com.tilinina.foursquaretest.retrofit.api;


import com.tilinina.foursquaretest.model.Groups;
import com.tilinina.foursquaretest.model.Photos;
import com.tilinina.foursquaretest.model.Venue;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface VenuesApi
{
  @GET("/explore")
  Call<List<Groups>> getExplore(@QueryMap Map<String, String> options);

  @GET("/search")
  Call<List<Venue>> getSearch(@QueryMap Map<String, String> options);

  @GET("/{id}/search")
  Call<Photos> getPhotos(@Path("id") int venueId);


}
