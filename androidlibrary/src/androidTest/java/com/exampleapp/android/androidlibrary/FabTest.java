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


    @Test
    public void testingFab() {
        //Find the view
        //Perform action on the view

        //openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        //onView((withId(com.exampleapp.android.androidlibrary.R.id.tripadvisor_fab)))
        //.perform(click());

        //onView(withText("Countries List")).perform(click());

        //Check if the view does what is expected
        //onView(withId(R.menu.menu)).check(matches(isEnabled()));

        onView(withId(R.id.tripadvisor_fab)).perform(click());


    }

}
