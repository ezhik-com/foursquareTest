package com.tilinina.foursquaretest.ui.activity;

import android.Manifest;

import android.content.Context;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import com.tilinina.foursquaretest.R;
import com.tilinina.foursquaretest.ui.fragment.VenueDetailsFragment;
import com.tilinina.foursquaretest.ui.fragment.VenueListFragment;

import java.io.IOException;

import java.util.List;
import java.util.Locale;


public class VenueActivity extends AppCompatActivity
{
  public static String TAG = "foursquaretest";

  public final static int PERMISSIONS_REQUEST_READ_CONTACTS = 200;

  LocationManager locationManager;
  LocationListener locationListener;
  Location location;
  String cityName = null;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.venue_activity_layout);

    locationManager = (LocationManager)
            getSystemService(Context.LOCATION_SERVICE);

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
        //TODO show dialog
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
      updateLocationData();
      if (location != null)
      {
        showListFragment();
      }
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
          updateLocationData();
          if (location != null)
          {
            showListFragment();
          }
        }
        else
        {
          // permission denied, boo! Disable the
          // functionality that depends on this permission.
          //TODO show dialog and try get cached data
        }
        return;
      }

      // other 'case' lines to check for other
      // permissions this app might request
    }
  }

  private void updateLocationData()
  {
    location = getLastBestLocation();
    cityName = getCityNameFromLocation();

    Toast.makeText(
            getBaseContext(),
            "Location changed: Lat: " + location.getLatitude() + " Lng: "
                    + location.getLongitude(), Toast.LENGTH_SHORT).show();
  }

  private String getCityNameFromLocation()
  {
    if (location == null)
    {
      return null;
    }
      /*------- To get city name from coordinates -------- */
    String cityName = null;
    Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
    List<Address> addresses;
    try
    {
      addresses = gcd.getFromLocation(location.getLatitude(),
              location.getLongitude(), 1);
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

    String s = location.getLatitude() + "\n" + location.getLongitude() + "\n\nMy Current City is: "
            + cityName;
    Log.v(TAG, s);

    return cityName;
  }


  private Location getLastBestLocation() throws SecurityException
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

  public void showListFragment()
  {
    VenueListFragment listFragment = new VenueListFragment();

    Bundle bundle = new Bundle();
    if (location != null)
    {
      bundle.putString(VenueListFragment.LOCATION_BUNDLE_KEY, String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude()));
    }
    if (cityName != null)
    {
      bundle.putString(VenueListFragment.CITY_BUNDLE_KEY, cityName);
    }

    listFragment.setArguments(bundle);

    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    ft.add(R.id.fragmentContainer, listFragment);
    ft.commit();
  }

  public void onVenueItemClick()
  {
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    ft.replace(R.id.fragmentContainer, new VenueDetailsFragment());
    ft.addToBackStack(VenueDetailsFragment.TAG);
    ft.commit();
  }

}
