package com.tilinina.foursquaretest;

import android.Manifest;
import android.content.Context;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.widget.Toast;


import com.tilinina.foursquaretest.model.Venue;
import com.tilinina.foursquaretest.retrofit.api.VenuesApi;
import com.tilinina.foursquaretest.ui.VenueAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
  public static String TAG = "foursquaretest";

  public final static int PERMISSIONS_REQUEST_READ_CONTACTS = 200;

  public final static String CLIENT_ID = "NX1DRVT5FPXO1YOC0530352DAOKG0XDHSMT3DDEWN3TBNHXD";
  public final static String CLIENT_SECRET= "OKLVSBICLJLAZN2KCZXW1ALPIRPEQ2BWNR2Y23ALSTQ1EJBY";

  LocationManager locationManager;
  LocationListener locationListener;
  Location location;
  VenuesApi venuesApi;
  ArrayList<Venue> venues;
  RecyclerView recyclerView;
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_layout);

    locationManager = (LocationManager)
            getSystemService(Context.LOCATION_SERVICE);

    locationListener = new MyLocationListener();

    venuesApi = FoursquareApp.getVenuesApi();

    venues = new ArrayList<>();

    recyclerView = (RecyclerView) findViewById(R.id.venues_recycle_view);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);

    VenueAdapter adapter = new VenueAdapter(venues);
    recyclerView.setAdapter(adapter);


    // Here, thisActivity is the current activity
    if (ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED)
    {

      // Should we show an explanation?
      if (ActivityCompat.shouldShowRequestPermissionRationale(this,
              Manifest.permission.ACCESS_FINE_LOCATION))
      {

        // Show an explanation to the user *asynchronously* -- don't block
        // this thread waiting for the user's response! After the user
        // sees the explanation, try again to request the permission.

      }
      else
      {

        // No explanation needed, we can request the permission.

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_REQUEST_READ_CONTACTS);

        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
        // app-defined int constant. The callback method gets the
        // result of the request.
      }
    }
    else
    {
      locationManager.requestLocationUpdates(
              LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
      location = getLastBestLocation();
      Toast.makeText(
              getBaseContext(),
              "Location changed: Lat: " + location.getLatitude() + " Lng: "
                      + location.getLongitude(), Toast.LENGTH_SHORT).show();
    }


  }

  @Override
  public void onRequestPermissionsResult(int requestCode,
                                         String permissions[], int[] grantResults)
  {
    switch (requestCode)
    {
      case PERMISSIONS_REQUEST_READ_CONTACTS:
      {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {

          // permission was granted, yay! Do the
          // contacts-related task you need to do.
          locationManager.requestLocationUpdates(
                  LocationManager.GPS_PROVIDER, 5000, 10, locationListener);

          location = getLastBestLocation();

          Toast.makeText(
                  getBaseContext(),
                  "Location changed: Lat: " + location.getLatitude() + " Lng: "
                          + location.getLongitude(), Toast.LENGTH_SHORT).show();

          getVenuesList();


        }
        else
        {

          // permission denied, boo! Disable the
          // functionality that depends on this permission.
        }
        return;
      }

      // other 'case' lines to check for other
      // permissions this app might request
    }
  }

  private Location getLastBestLocation()
  {
    Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    Location locationNet = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

    long GPSLocationTime = 0;
    if (null != locationGPS)
    {
      GPSLocationTime = locationGPS.getTime();
    }

    long NetLocationTime = 0;

    if (null != locationNet)
    {
      NetLocationTime = locationNet.getTime();
    }

    if (0 < GPSLocationTime - NetLocationTime)
    {
      return locationGPS;
    }
    else
    {
      return locationNet;
    }
  }

  private void getVenuesList()
  {
    Map<String, String> options = new HashMap<>();

    options.put("client_id", CLIENT_ID);
    options.put("client_secret", CLIENT_SECRET);
    options.put("ll",  String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude()));
    options.put("limit", "20");
    options.put("radius", "50000");

    venuesApi.getSearch(options).enqueue(new Callback<List<Venue>>()
  {
    @Override
    public void onResponse(Call<List<Venue>> call, Response<List<Venue>> response)
    {

      if (response.body()!= null)
      {
        venues.addAll(response.body());
        recyclerView.getAdapter().notifyDataSetChanged();
      }
    }

    @Override
    public void onFailure(Call<List<Venue>> call, Throwable t)
    {

    }
  });
  }


  /*---------- Listener class to get coordinates ------------- */
  private class MyLocationListener implements LocationListener
  {

    @Override
    public void onLocationChanged(Location loc)
    {
      Toast.makeText(
              getBaseContext(),
              "Location changed: Lat: " + loc.getLatitude() + " Lng: "
                      + loc.getLongitude(), Toast.LENGTH_SHORT).show();
      String longitude = "Longitude: " + loc.getLongitude();
      Log.v(TAG, longitude);
      String latitude = "Latitude: " + loc.getLatitude();
      Log.v(TAG, latitude);

        /*------- To get city name from coordinates -------- */
      String cityName = null;
      Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
      List<Address> addresses;
      try
      {
        addresses = gcd.getFromLocation(loc.getLatitude(),
                loc.getLongitude(), 1);
        if (addresses.size() > 0)
        {
          System.out.println(addresses.get(0).getLocality());
          cityName = addresses.get(0).getLocality();
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      String s = longitude + "\n" + latitude + "\n\nMy Current City is: "
              + cityName;
      Log.v(TAG, s);

    }

    @Override
    public void onProviderDisabled(String provider)
    {
      Log.v(TAG, "onProviderDisabled");
    }

    @Override
    public void onProviderEnabled(String provider)
    {
      Log.v(TAG, "onProviderEnabled");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
      Log.v(TAG, "onStatusChanged");
    }
  }

}
