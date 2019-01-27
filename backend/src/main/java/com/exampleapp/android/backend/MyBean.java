package com.exampleapp.android.backend;

/**
 * Created by Aiman Nabeel on 24/10/2018.
 */

import com.exampleapp.android.countrieslibrary.CountryData;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private CountryData myData;

    public CountryData getData() {
        return myData;
    }

    public void setData(CountryData data) {
        myData = data;
    }
}
