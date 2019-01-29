package com.exampleapp.android.capstone_project;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import android.util.Log;
import android.util.Pair;
import static org.junit.Assert.*;

/**
 * Created by Aiman Nabeel on 29/01/2019.
 */
//To test if async task is working
@RunWith(AndroidJUnit4.class)
public class CountryDisplayTest {

    private static final String TAG = CountryDisplayTest.class.getSimpleName();
    //Extracting context using InstrumentationRegistry
    Context context = InstrumentationRegistry.getTargetContext();
    EndpointsAsyncTask.Callback callback;

    @Test
    public void testAsyncTask() {

        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(callback);
        //Extracting context using InstrumentationRegistry
        //Context context = InstrumentationRegistry.getTargetContext();
        //asyncTask.execute(new Pair<Context, String>(context, "Manfred"));
        asyncTask.execute((new Pair<Context, String>(context, "Manfred")));

        try {
            String countryName = asyncTask.get();
            Log.d(TAG, "Joke text: " + countryName);
            //assertNotNull(countryName);
            assertTrue(countryName.length() > 0);
        }

        catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }
}
