package com.exampleapp.android.androidlibrary;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Aiman Nabeel on 08/11/2018.
 */
//To save the names of random countries displayed on every swipe
public class CountriesDBContract {

    //The authority, which is how your code knows which Content Provider to access
    public static final String AUTHORITY = "com.exampleapp.android.androidlibrary";

    //The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    //This is the path for the random_countries_list directory
    public static final String PATH_RANDOM_COUNTRIES = "randomcountrieslist";

    public static final class RandomCountriesList implements BaseColumns {

        //ListEntry content URI = base content URI + path
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_RANDOM_COUNTRIES).build();

        public static final String TABLE_NAME = "randomcountrieslist";
        public static final String COLUMN_RANDOM_COUNTRY_NAME = "randomcountryname";
        public static final String COLUMN_RANDOM_COUNTRY_ID = "randomcountryid";
        //public static final String COLUMN_RANDOM_COUNTRY_LAT = "randomcountrylat";
        //public static final String COLUMN_RANDOM_COUNTRY_LNG = "randomcountrylng";
    }
}
