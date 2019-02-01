package com.exampleapp.android.androidlibrary;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.exampleapp.android.countrieslibrary.CountryData;
import com.google.gson.Gson;

import java.util.Observable;

import io.reactivex.ObservableOnSubscribe;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by Aiman Nabeel on 24/10/2018.
 */
//Ref: https://developers.google.com/maps/documentation/android-sdk/start
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "@@@@@#######::";
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 111;

    private GoogleMap mMap;
    public static final String COUNTRIES_KEY_EXTRA = "country";
    CountryData countryData;

    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLastLocation;
    private double mLatitudeLabel;
    private double mLongitudeLabel;

    //Location service - related code
    //Ref: https://developer.android.com/training/location/retrieve-current#permissions
    //Ref: https://iteritory.com/android-location-service-tutorial-fetch-users-last-known-location/
    @Override
    public void onStart() {
        super.onStart();
        if (!checkPermissions()) {
            Log.i(TAG, "Inside onStart function; requesting permission when permission is not available");
            requestPermissions();
        } else {
            Log.i(TAG, "Inside onStart function; getting location when permission is already available");
            getLastLocation();
        }
    }

    //Return whether permissions is needed as boolean value.
    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    //Request permission from user
    private void requestPermissions() {
        Log.i(TAG, "Inside requestPermissions function");
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

        //Log an additional rationale to the user. This would happen if the user denied the
        //request previously, but didn't check the "Don't ask again" checkbox.
        // In case you want, you can also show snackbar. Here, we used Log just to clear the concept.
        if (shouldProvideRationale) {
            Log.i(TAG, "****Inside requestPermissions function when shouldProvideRationale = true");
            startLocationPermissionRequest();
        } else {
            Log.i(TAG, "****Inside requestPermissions function when shouldProvideRationale = false");
            startLocationPermissionRequest();
        }
    }

    //Start the permission request dialog
    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(MapsActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    /**
     * Callback to the following function is received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                // user interaction is cancelled; in such case we will receive empty grantResults[]
                //In such case, just record/log it.
                Log.i(TAG, "User interaction has been cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted by the user.
                Log.i(TAG, "User permission has been given. Now getting location");
                getLastLocation();
            } else {
                // Permission is denied by the user.
                Log.i(TAG, "User denied permission.");
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mFusedLocationClient.getLastLocation()
                .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            mLastLocation = task.getResult();
                            //mLatitudeText.setText(String.format(Locale.ENGLISH, "%s: %f", mLatitudeLabel, mLastLocation.getLatitude()));
                            //mLongitudeText.setText(String.format(Locale.ENGLISH, "%s: %f",mLongitudeLabel, mLastLocation.getLongitude()));
                            mLatitudeLabel = mLastLocation.getLatitude();
                            mLongitudeLabel = mLastLocation.getLongitude();

                            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                            //Q: Why it's important to set getMapAsync in getLastLocation and not in onCreate?
                            //A: The problem occurs because both getMapAsync() and getLastLocation() depend on a listener, which we don't know
                            //exactly when they will be called. But we need to make sure we first set the latitude / longitude, before using them in the onMapReady().
                            final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                                    .findFragmentById(R.id.map);

                            mapFragment.getMapAsync(MapsActivity.this);
                        } else {
                            Log.i(TAG, "Inside getLocation function. Error while getting location");
                            System.out.println(TAG + task.getException());
                        }
                    }
                });
    }
    //End of Location service - related code

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Using Shared preferences here to get the saved CountryData object from CountriesFragment.java
        //https://stackoverflow.com/questions/5418160/store-and-retrieve-a-class-object-in-shared-preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = prefs.getString("country", "");
        CountryData countryData = gson.fromJson(json, CountryData.class);

        // Add a marker in user's current location
        LatLng yourLocation = new LatLng(mLatitudeLabel, mLongitudeLabel);
        mMap.addMarker(new MarkerOptions().position(yourLocation).title("Your Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(yourLocation));

        // Add a marker in country and move the camera
        //LatLng sydney = new LatLng(-34, 151); - For Testing
        //double countryLat = countryData.getCountryLat(); - For Testing
        LatLng countryNav = new LatLng(countryData.getCountryLat(), countryData.getCountryLng());
        mMap.addMarker(new MarkerOptions().position(countryNav).title(countryData.getCountryName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(countryNav));
    }
}
