package com.tilinina.foursquaretest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBody
{
  @SerializedName("venues")
  @Expose
  private List<Venue> venues;

  public List<Venue> getVenues()
  {
    return venues;
  }

  public void setVenues(List<Venue> venues)
  {
    this.venues = venues;
  }
}
