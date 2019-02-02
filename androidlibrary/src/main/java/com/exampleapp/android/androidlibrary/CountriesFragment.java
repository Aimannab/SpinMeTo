package com.exampleapp.android.androidlibrary;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exampleapp.android.countrieslibrary.CountryData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aiman Nabeel on 24/10/2018.
 */
public class CountriesFragment extends Fragment {

    public static final String COUNTRIES_KEY_EXTRA = "country";
    public static final String COUNTRIES_KEY_EXTRA_ID = "id";

    public CountriesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_countries, container, false);
        setRandomCountry(view);

        //Connecting Share FAB
        view.findViewById(R.id.share_fab).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = getActivity().getIntent();
                CountryData country = (CountryData) intent.getExtras().getSerializable(COUNTRIES_KEY_EXTRA);

                // TODO Add app link once shared on Google Play Store
                startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(getActivity())
                        .setType("text/plain")
                        .setText("#SpinMeTo " + country.getCountryName())
                        .getIntent(), getString(R.string.action_share)));
            }
        });

        //Connecting Google Maps FAB
        view.findViewById(R.id.map_fab).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

        //Connecting Skyscanner FAB
        view.findViewById(R.id.skyscanner_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.skyscanner.net/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }
        });

        //Connecting Tripadvisor FAB
        view.findViewById(R.id.tripadvisor_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.tripadvisor.co.uk/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }
        });

        //update widget
        //CountriesWidgetService.startRandomCountriesListService(getContext(), country);

        return view;
    }


    private void setRandomCountry(View view) {
        Intent intent = getActivity().getIntent();
        //String country = intent.getStringExtra(COUNTRIES_KEY_EXTRA);
        //String id = intent.getStringExtra(COUNTRIES_KEY_EXTRA_ID);
        CountryData country = (CountryData) intent.getExtras().getSerializable(COUNTRIES_KEY_EXTRA);

        if (country != null) {
            TextView textView = view.findViewById(R.id.random_country);
            textView.setText(country.getCountryName());
        }

        if (country != null) {

            //Saving random country names in DB
            List<ContentValues> list = new ArrayList<ContentValues>();
            Context context = view.getContext();
            ContentValues cv = new ContentValues();
            cv.put(CountriesDBContract.RandomCountriesList.COLUMN_RANDOM_COUNTRY_NAME, country.getCountryName());
            //cv.put(CountriesDBContract.RandomCountriesList.COLUMN_RANDOM_COUNTRY_LAT, country.getCountryLat());
            //cv.put(CountriesDBContract.RandomCountriesList.COLUMN_RANDOM_COUNTRY_LNG, country.getCountryLng());
            //cv.put(CountriesDBContract.RandomCountriesList.COLUMN_RANDOM_COUNTRY_ID, id);

            list.add(cv);

            //Inserting new random country name via ContentResolver
            Uri uri = getActivity().getContentResolver().insert(CountriesDBContract.RandomCountriesList.CONTENT_URI, cv);

            //Saving CountryData object here to be used in MapsActivity.java + GridWidgetService for widget
            //https://stackoverflow.com/questions/5418160/store-and-retrieve-a-class-object-in-shared-preference
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor prefsEditor = prefs.edit();
            Gson gson = new Gson();
            String json = gson.toJson(country); // myObject - instance of MyObject
            prefsEditor.putString("country", json);
            prefsEditor.commit();
        }
    }
}
