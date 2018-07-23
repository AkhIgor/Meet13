package com.example.meet13;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Игорь on 16.07.2018.
 */

public class Model implements MainManager.Model {

    private Weather weather;
    private MyDataBase myDataBase;

    public Model(MyDataBase myDataBase) {
        this.myDataBase = myDataBase;
    }

    @Override
    public Weather getWeather() {
        try {
            return new GetWeather(myDataBase.weatherDao()).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetWeather extends AsyncTask<Void, Void, Weather> {

        private WeatherDao dao;

        private GetWeather(WeatherDao dao) {
            this.dao = dao;
        }

        @Override
        protected Weather doInBackground(Void... voids) {
            Weather weather = dao.getForecast();

            long time = new Date().getTime();
            long dailyTime = (time - 86400000) / 1000;
            long hourlyTime = (time - 3600000) / 1000;

            List<DailyForecast> dailyData = dao.getDailyForecast(dailyTime);
            List<HourlyForecast> hourlyData = dao.getHourlyForecast(hourlyTime);

            weather.getDaily().setForecast(dailyData);
            weather.getHourly().setForecast(hourlyData);
            return weather;
        }
    }
}
