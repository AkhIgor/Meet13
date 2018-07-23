package com.example.meet13;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Игорь on 16.07.2018.
 */

public class MyRetrofit {

    private static final String BASIC_URL = "https://api.darksky.net/forecast/7713d0cdefa96f6134c75945467058b5/";

    public WebService getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                                                .baseUrl(BASIC_URL)
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .build();

        return retrofit.create(WebService.class);
    }
}
