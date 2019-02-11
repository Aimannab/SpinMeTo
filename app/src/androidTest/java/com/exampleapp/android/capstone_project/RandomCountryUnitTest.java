package com.exampleapp.android.capstone_project;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;
import android.util.Pair;
import static org.junit.Assert.*;


/**
 * Created by Aiman Nabeel on 11/02/2019.
 */
@RunWith(AndroidJUnit4.class)
public class RandomCountryUnitTest {

    private static final String TAG = RandomCountryUnitTest.class.getSimpleName();

    //Extracting context using InstrumentationRegistry
    Context context = InstrumentationRegistry.getTargetContext();
    EndpointsAsyncTask.Callback callback;

    @Test
    public void testAsyncTask() {

        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(callback);
        asyncTask.execute(new Pair<Context, String>(context, "Manfred"));

        try {
            String country = asyncTask.get();
            Log.d(TAG, "Country name: " + country);
            //assertNotNull(country);
            assertTrue(country.length()>0);

        }

        catch (Exception e) {
                Log.e(TAG, Log.getStackTraceString(e));
        }

    }

}
