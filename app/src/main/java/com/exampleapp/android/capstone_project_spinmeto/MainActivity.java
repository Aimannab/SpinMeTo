package com.exampleapp.android.capstone_project_spinmeto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.exampleapp.android.androidlibrary.CountriesActivity;
import com.exampleapp.android.backend.myApi.MyApi;
import com.exampleapp.android.countrieslibrary.Countries;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import android.support.v7.widget.Toolbar;

import static com.exampleapp.android.androidlibrary.CountriesFragment.COUNTRIES_KEY_EXTRA;

/**
 * Created by Aiman Nabeel on 17/10/2018.
 */

class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    //public static String AsyncError = "Error Getting Country";


    //Ref: https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/77e9910911d5412e5efede5fa681ec105a0f02ad/HelloEndpoints#2-connecting-your-android-app-to-the-backend
    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            return String.valueOf(myApiService.tellRandomCountry().execute().getData());
        } catch (IOException e) {
            //e.printStackTrace();
            //return AsyncError;
            //return e.getMessage();
            return null;
        }
    }

    private Callback callback;

    public interface Callback {
        void onFinished(String result);
    }

    public EndpointsAsyncTask(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        if (result != null) {
            callback.onFinished(result);
        }
    }
}


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.Callback {
    TextView tvSwipeDescription;

    @SuppressLint({"ClickableViewAccessibility", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary)); //status bar or the time bar at the top


        //Swipe code
        //Ref:https://www.spaceotechnologies.com/android-swipe-gestures-tutorial/
        //Ref:https://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures
        initializeView();
        tvSwipeDescription.setOnTouchListener(new OnSwipeTouchListner(MainActivity.this) {
            public void onSwipeTop() {
            }

            public void onSwipeRight() {
                //Starts DetailActivity - Displays random country
                Countries tellCountry = new Countries();
                Intent intent = new Intent(MainActivity.this, CountriesActivity.class);
                intent.putExtra(COUNTRIES_KEY_EXTRA, tellCountry.selectRandomCountry());
                startActivity(intent);

                new EndpointsAsyncTask(MainActivity.this).execute(new Pair<Context, String>(MainActivity.this, "Manfred"));
            }

            public void onSwipeLeft() {
            }

            public void onSwipeBottom() {
            }
        });
    }

    private void initializeView() {
        tvSwipeDescription = (TextView) findViewById(R.id.tvSwipeDescription);
    }

    //In case a button is used
    public void tellRandomCountry(View view) {
        Countries tellCountry = new Countries();
        Intent intent = new Intent(this, CountriesActivity.class);
        intent.putExtra(COUNTRIES_KEY_EXTRA, tellCountry.selectRandomCountry());
        startActivity(intent);

        new EndpointsAsyncTask(this).execute(new Pair<Context, String>(this, "Manfred"));

    }

    @Override
    public void onFinished(String result) {
        Intent intent = new Intent(this, CountriesActivity.class);
        //intent.putExtra(Intent.EXTRA_TEXT, result);

        Bundle bundle = new Bundle();
        bundle.putSerializable(COUNTRIES_KEY_EXTRA, result);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}