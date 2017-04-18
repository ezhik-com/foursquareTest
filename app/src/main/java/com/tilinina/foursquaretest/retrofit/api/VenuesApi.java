package com.tilinina.foursquaretest.retrofit.api;


import com.tilinina.foursquaretest.model.Groups;
import com.tilinina.foursquaretest.model.Photos;
import com.tilinina.foursquaretest.model.SearchResponse;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface VenuesApi
{
  @GET("/v2/venues/explore")
  Call<List<Groups>> getExplore(@QueryMap Map<String, String> options);

  @GET("/v2/venues/search")
  Call<SearchResponse> getSearch(@QueryMap Map<String, String> options);

  @GET("/v2/venues/{id}/search")
  Call<Photos> getPhotos(@Path("id") int venueId,@QueryMap Map<String, String> options);


}
