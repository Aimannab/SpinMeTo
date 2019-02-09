package com.exampleapp.android.androidlibrary;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class IntentTest {

   /*@Rule
   public IntentsTestRule<MainActivity> mIntentTestRule = new IntentsTestRule<>(CountriesFragment.class);

    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void clickRecipeButton_opensRecipeDetailActivity() {
        //1.Find the view
        //2.Perform action on the view
        //3.Check if the view does what is expected

        //Ref: https://spin.atomicobject.com/2016/04/15/espresso-testing-recyclerviews/
        onView(ViewMatchers.withId(R.id.recipe_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        //Ref:https://developer.android.com/training/testing/espresso/intents
        intended(hasComponent(RecipeDetailActivity.class.getName()));
    }*/


}
