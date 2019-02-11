package com.exampleapp.android.androidlibrary;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;



//AndroidJUnit4 -> Helps control launching the app + running UI tests on it
@RunWith(AndroidJUnit4.class)
public class FabTest {

    @Rule
    //provides functional testing for a specific, single activity
    public ActivityTestRule<CountriesActivity> mActivityTestRule =
            new ActivityTestRule<>(CountriesActivity.class);

    @Before
    public void yourSetupFragment() {
        mActivityTestRule.getActivity()
                .getFragmentManager().beginTransaction();
    }


    //https://android.jlelse.eu/the-basics-of-android-espresso-testing-activities-fragments-7a8bfbc16dc5
    @Test
    public void testingFab() {
        //Find the view
        //Perform action on the view

        //REM: Only works when setRandomCountry() from CountriesFragment is commented out! Because otherwise you get error since setRandomCountry() is connected with the swipe gesture
        onView(withId(R.id.tripadvisor_fab)).perform(click());


    }

}
