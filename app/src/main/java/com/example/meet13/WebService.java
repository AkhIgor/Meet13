package com.example.meet13;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Игорь on 14.07.2018.
 */

public interface WebService {
    @GET("{latitude},{longitude}?exclude=currently,minutely,alerts,flags&units=si&lang=ru")
    Call<Weather> getWeekForecast(@Path("latitude") double latitude, @Path("longitude") double longitude);
}
