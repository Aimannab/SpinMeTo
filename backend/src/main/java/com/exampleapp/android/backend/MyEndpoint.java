package com.exampleapp.android.backend;

import com.exampleapp.android.countrieslibrary.Countries;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * Created by Aiman Nabeel on 24/10/2018.
 */

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.android.exampleapp.com",
                ownerName = "backend.android.exampleapp.com",
                packagePath = ""
        )
)

public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "tellRandomCountry")
    public MyBean tellRandomCountry() {
        MyBean response = new MyBean();

        Countries randomCounty = new Countries();
        response.setData(randomCounty.selectRandomCountry());

        return response;
    }

}
