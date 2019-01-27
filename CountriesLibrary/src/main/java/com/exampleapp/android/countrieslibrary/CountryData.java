package com.exampleapp.android.countrieslibrary;

//import android.os.Parcel;
//import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Aiman Nabeel on 29/11/2018.
 */
//Parcelable cannot be resolved even after imports. Uncomment code and see
public class CountryData implements Serializable /*implements Parcelable*/ {

    private String data1;
    private double data2;
    private double data3;

    public CountryData(String countryName, double lat, double lng) {

        data1 = countryName;
        data2 = lat;
        data3 = lng;

    }

    public double getCountryLat() {

        return data2;
    }

    public double getCountryLng() {

        return data3;
    }

    public String getCountryName() {

        return data1;
    }

    public void setCountryLat(double lat) {
        data2 = lat;
    }

    public void setCountryLng(double lng) {
        data3 = lng;
    }

    public void setCountryName(String name) {
        data1 = name;
    }
}
