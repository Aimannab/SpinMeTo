package com.exampleapp.android.capstone_project;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.withId;



//AndroidJUnit4 -> Helps control launching the app + running UI tests on it
@RunWith(AndroidJUnit4.class)
public class SwipeTest {

    @Rule
    //provides functional testing for a specific, single activity
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testingSwipeGesture() {
        //Find the view
        //Perform action on the view
        //Check if the view does what is expected

        onView(withId(R.id.tvSwipeDescription)).perform(swipeRight());

    }

}
