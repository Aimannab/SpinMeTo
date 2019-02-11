package com.exampleapp.android.androidlibrary;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class MenuTest {


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
    public void testingMenu() {

        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        // Click the item.
        onView(withText("Countries List")).perform(click());

    }
}
