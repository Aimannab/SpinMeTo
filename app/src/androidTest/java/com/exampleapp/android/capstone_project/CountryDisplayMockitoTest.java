package com.exampleapp.android.capstone_project;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;
import android.util.Pair;

import com.exampleapp.android.backend.myApi.MyApi;
import com.exampleapp.android.capstone_project.CountryDisplayTest;
import com.exampleapp.android.capstone_project.EndpointsAsyncTask;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;
import java.util.MissingFormatArgumentException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by Aiman Nabeel on 29/01/2019.
 */
//Not working - incomplete
public class CountryDisplayMockitoTest {

    MainActivity mainActivity;
    private static MyApi myApiService = null;
    private Context context;

    //@Mock
    //EndpointsAsyncTask asyncTask;

    //@Captor
    //EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(EndpointsAsyncTask.Callback);


    @Test
    public void testAsyncTask(Pair<Context, String>... params) {

        /*if (myApiService == null) {  // Only do this once
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
    }*/
    }
}
