package com.tilinina.foursquaretest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse
{

  @SerializedName("response")
  @Expose
  private ResponseBody responseBody;

  public ResponseBody getResponseBody()
  {
    return responseBody;
  }

  public void setResponseBody(ResponseBody responseBody)
  {
    this.responseBody = responseBody;
  }
}



//{
//        "venue": {
//        "id": "string",
//        "name": "string",
//        "contact":{
//        "phone": "string",
//        "formattedPhone": "string"},
//        "location": {
//        "address": "string",
//        "crossStreet": "string",
//        "lat": "double",
//        "lng": "double",
//        "distance": "integer",
//        "cc": "string",
//        "city": "string",
//        "state": "string",
//        "country": "string",
//        "formattedAddress": [
//        "string",
//        "string",
//        "string"
//        ]
//        },
//        "categories": [
//        {
//        "id": "string",
//        "name": "string",
//        "pluralName": "string",
//        "shortName": "string",
//        "icon": {
//        "prefix": "string",
//        "suffix": "string"},
//        "primary": "boolean"
//        }
//        ],
//        "verified": "boolean",
//        "url": "string",
//        "referralId": "string"
//
//        }
//        }