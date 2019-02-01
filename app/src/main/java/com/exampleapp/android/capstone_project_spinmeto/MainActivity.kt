package com.exampleapp.android.capstone_project_spinmeto

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Pair
import android.view.View
import android.widget.TextView

import com.exampleapp.android.androidlibrary.CountriesActivity
import com.exampleapp.android.backend.myApi.MyApi
import com.exampleapp.android.countrieslibrary.Countries
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.extensions.android.json.AndroidJsonFactory
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer

import java.io.IOException

import android.support.v7.widget.Toolbar

import com.exampleapp.android.androidlibrary.CountriesFragment.COUNTRIES_KEY_EXTRA

/**
 * Created by Aiman Nabeel on 17/10/2018.
 */

internal class EndpointsAsyncTask(private val callback: Callback) : AsyncTask<Pair<Context, String>, Void, String>() {
    private var context: Context? = null
    //public static String AsyncError = "Error Getting Country";


    //Ref: https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/77e9910911d5412e5efede5fa681ec105a0f02ad/HelloEndpoints#2-connecting-your-android-app-to-the-backend
    override fun doInBackground(vararg params: Pair<Context, String>): String? {
        if (myApiService == null) {  // Only do this once
            val builder = MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer { abstractGoogleClientRequest -> abstractGoogleClientRequest.disableGZipContent = true }
            // end options for devappserver

            myApiService = builder.build()
        }

        context = params[0].first
        val name = params[0].second

        try {
            return myApiService!!.tellRandomCountry().execute().data.toString()
        } catch (e: IOException) {
            //e.printStackTrace();
            //return AsyncError;
            //return e.getMessage();
            return null
        }

    }

    interface Callback {
        fun onFinished(result: String)
    }

    override fun onPostExecute(result: String?) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        if (result != null) {
            callback.onFinished(result)
        }
    }

    companion object {
        private var myApiService: MyApi? = null
    }
}


class MainActivity : AppCompatActivity(), EndpointsAsyncTask.Callback {
    internal lateinit var tvSwipeDescription: TextView

    @SuppressLint("ClickableViewAccessibility", "NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myToolbar = findViewById<View>(R.id.my_toolbar) as Toolbar
        setSupportActionBar(myToolbar)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary) //status bar or the time bar at the top


        //Swipe code
        //Ref:https://www.spaceotechnologies.com/android-swipe-gestures-tutorial/
        //Ref:https://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures
        initializeView()
        tvSwipeDescription.setOnTouchListener(object : OnSwipeTouchListner(this@MainActivity) {
            override fun onSwipeTop() {}

            override fun onSwipeRight() {
                //Starts DetailActivity - Displays random country
                val tellCountry = Countries()
                val intent = Intent(this@MainActivity, CountriesActivity::class.java)
                intent.putExtra(COUNTRIES_KEY_EXTRA, tellCountry.selectRandomCountry())
                startActivity(intent)

                EndpointsAsyncTask(this@MainActivity).execute(Pair(this@MainActivity, "Manfred"))
            }

            override fun onSwipeLeft() {}

            override fun onSwipeBottom() {}
        })
    }

    private fun initializeView() {
        tvSwipeDescription = findViewById<View>(R.id.tvSwipeDescription) as TextView
    }

    //In case a button is used
    fun tellRandomCountry(view: View) {
        val tellCountry = Countries()
        val intent = Intent(this, CountriesActivity::class.java)
        intent.putExtra(COUNTRIES_KEY_EXTRA, tellCountry.selectRandomCountry())
        startActivity(intent)

        EndpointsAsyncTask(this).execute(Pair(this, "Manfred"))

    }

    override fun onFinished(result: String) {
        val intent = Intent(this, CountriesActivity::class.java)
        //intent.putExtra(Intent.EXTRA_TEXT, result);

        val bundle = Bundle()
        bundle.putSerializable(COUNTRIES_KEY_EXTRA, result)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}