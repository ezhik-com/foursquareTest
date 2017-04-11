package com.tilinina.foursquaretest.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Venue {

  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("contact")
  @Expose
  private Contact contact;
  @SerializedName("location")
  @Expose
  private Location location;
  @SerializedName("categories")
  @Expose
  private List<Category> categories = null;
  @SerializedName("verified")
  @Expose
  private String verified;
  @SerializedName("url")
  @Expose
  private String url;
  @SerializedName("referralId")
  @Expose
  private String referralId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  public String getVerified() {
    return verified;
  }

  public void setVerified(String verified) {
    this.verified = verified;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getReferralId() {
    return referralId;
  }

  public void setReferralId(String referralId) {
    this.referralId = referralId;
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