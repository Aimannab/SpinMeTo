package com.exampleapp.android.capstone_project;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;
import android.util.Pair;

import com.exampleapp.android.countrieslibrary.Countries;
import com.exampleapp.android.countrieslibrary.CountryData;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
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

    @Rule
    //provides functional testing for a specific, single activity
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testAsyncTask() {

        onView(withId(R.id.tvSwipeDescription)).perform(swipeRight());

        try {
            //String country = asyncTask.get().;
            Countries tellCountry = new Countries();

            CountryData country = tellCountry.selectRandomCountry();
            assertNotNull(country);
            Log.d(TAG, "Country name: " + country);
            //assertTrue(country.length()< 0);

        }

        catch (Exception e) {
                Log.e(TAG, Log.getStackTraceString(e));
        }

    }

}
