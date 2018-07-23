package com.example.meet13;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Response;

/**
 * Created by Игорь on 16.07.2018.
 */

public class myService extends IntentService {

    public myService() {
        super("Service name");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        MyRetrofit myRetrofit = new MyRetrofit();
        MyDataBase myDataBase = MyDataBase.getInstance(getBaseContext());
        WeatherDao weatherDao = myDataBase.weatherDao();

        try {
            List<Double> coordinates;
            boolean changed = false;

            if(intent != null)
                changed = intent.getBooleanExtra(SettingsActivity.CHANGED, false);

            coordinates = changed ? getNewCoordinates() : getPreviousCoordinates();

            Response<Weather> results = myRetrofit.getRetrofit()
                    .getWeekForecast(coordinates.get(0), coordinates.get(1))
                    .execute();

            Weather weather = results.body();

                assert weather != null;
                List<HourlyForecast> forecasts = weather.getHourly().getForecast().subList(0, 24);
                weather.getHourly().setForecast(forecasts);

            Intent broadcast = new Intent(MainActivity.BROADCAST).putExtra(MainActivity.WEATHER, weather);
            sendBroadcast(broadcast);
            Log.d("TAG", "broadcast was sent");

            myDataBase.clearAllTables();
            weatherDao.addDailyForecast(weather.getDaily().getForecast());
            weatherDao.addHourlyForecast(weather.getHourly().getForecast());
            weatherDao.addForecast(weather);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Double> getPreviousCoordinates() {
        List<Double> coordinates= new ArrayList<>(2);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        coordinates.add((double) preferences.getFloat("latitude", 55.75222f));
        coordinates.add((double) preferences.getFloat("longitude", 37.61556f));

        return coordinates;
    }

    private List<Double> getNewCoordinates() throws IOException {

        List<Double> coordinates = new ArrayList<>(2);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String location = preferences.getString("location", "Moscow");

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocationName(location, 1);

        if(addresses != null && !addresses.isEmpty()) {
            double latitude = addresses.get(0).getLatitude();
            double longitude = addresses.get(1).getLongitude();

            coordinates.add(latitude);
            coordinates.add(longitude);

            SharedPreferences.Editor editor = preferences.edit();

            editor.putFloat("latitude", (float) latitude);
            editor.putFloat("longitude", (float) longitude);
            editor.apply();
        }

        return coordinates;
    }
}
