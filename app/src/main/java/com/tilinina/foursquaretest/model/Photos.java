package com.tilinina.foursquaretest.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photos {

  @SerializedName("count")
  @Expose
  private String count;
  @SerializedName("items")
  @Expose
  private List<Item> items = null;

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

}

//{"photos": {
//        "count": "integer",
//        "items": [
//        {
//        "id": "string",
//        "createdAt": "data",
//
//        "prefix": "string",
//        "suffix": "string",
//        "width": "integer",
//        "height": "integer",
//        "user": {
//        "id": "string",
//        "firstName": "string",
//        "lastName": "string",
//        "gender": "string",
//        "photo": {
//        "prefix": "string",
//        "suffix": "string"
//        }
//
//        }},
//        {
//        "id": "string",
//        "createdAt": "data",
//        "prefix": "string",
//        "suffix": "string",
//        "width": "integer",
//        "height": "integer",
//        "user": {
//        "id": "string",
//        "firstName": "string",
//        "lastName": "string",
//        "gender": "string",
//        "photo": {
//        "prefix": "string",
//        "suffix": "string"
//        }
//        }
//
//        }
//        ]
//        }
//        }