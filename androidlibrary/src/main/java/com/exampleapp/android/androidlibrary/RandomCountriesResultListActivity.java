package com.exampleapp.android.androidlibrary;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by Aiman Nabeel on 09/11/2018.
 */
public class RandomCountriesResultListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private SQLiteDatabase db;
    private RandomCountriesResultListAdapter mAdapter;
    private static final String TAG = RandomCountriesResultListActivity.class.getSimpleName();

    public static final String[] MAIN_RANDOM_COUNTRIES_PROJECTION = {
            CountriesDBContract.RandomCountriesList.COLUMN_RANDOM_COUNTRY_ID,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ref: https://stackoverflow.com/questions/3389501/activity-transition-in-android
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.all_random_movies_view);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);// Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        //ab.setDisplayHomeAsUpEnabled(true);

        RecyclerView randomCoutriesRecyclerView;
        randomCoutriesRecyclerView = new RecyclerView(this);
        randomCoutriesRecyclerView = (RecyclerView) this.findViewById(R.id.all_random_movies_view);
        randomCoutriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        CountriesDBHelper dbHelper = new CountriesDBHelper(this);
        db = dbHelper.getWritableDatabase();

        Cursor cursor = getAllRandomCountries();
        mAdapter = new RandomCountriesResultListAdapter(this, cursor);
        //Linking the RandomCountries adapter to RecyclerView
        randomCoutriesRecyclerView.setAdapter(mAdapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary)); //status bar or the time bar at the top
        }

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new AsyncTaskLoader<Cursor>(this) {

            // Initializing a Cursor, this will hold all the task data
            Cursor mRandomCountriesData = null;

            // onStartLoading() is called when a loader first starts loading data
            @Override
            protected void onStartLoading() {
                if (mRandomCountriesData != null) {
                    // Delivers any previously loaded data immediately

                    deliverResult(mRandomCountriesData);
                } else {
                    // Force a new load
                    forceLoad();
                }
            }

            // loadInBackground() performs asynchronous loading of data
            @Override
            public Cursor loadInBackground() {
                // Will implement to load data

                // Query and load all task data in the background; sort by priority
                // [Hint] use a try/catch block to catch any errors in loading data

                try {
                    return getContentResolver().query(CountriesDBContract.RandomCountriesList.CONTENT_URI,
                            null,
                            null,
                            null,
                            CountriesDBContract.RandomCountriesList.COLUMN_RANDOM_COUNTRY_NAME);

                } catch (Exception e) {
                    Log.e(TAG, "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }

            // deliverResult sends the result of the load, a Cursor, to the registered listener
            public void deliverResult(Cursor data) {
                mRandomCountriesData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        // Updating the data that the adapter uses to create ViewHolders
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mAdapter.swapCursor(null);

    }

    private Cursor getAllRandomCountries() {
        return db.query(CountriesDBContract.RandomCountriesList.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
