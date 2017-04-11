package com.tilinina.foursquaretest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("createdAt")
  @Expose
  private String createdAt;
  @SerializedName("prefix")
  @Expose
  private String prefix;
  @SerializedName("suffix")
  @Expose
  private String suffix;
  @SerializedName("width")
  @Expose
  private String width;
  @SerializedName("height")
  @Expose
  private String height;
  @SerializedName("user")
  @Expose
  private User user;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getSuffix() {
    return suffix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  public String getWidth() {
    return width;
  }

  public void setWidth(String width) {
    this.width = width;
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
