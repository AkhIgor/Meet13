package com.example.meet13;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

/**
 * Created by Игорь on 15.07.2018.
 */
@Dao
public abstract class WeatherDao {

    @Query("SELECT * FROM weather")
    public abstract Weather getForecast();

    @Insert
    public abstract void addForecast(Weather weather);

    @Query("SELECT * FROM HourlyForecast WHERE time >= :time")
    public abstract List<HourlyForecast> getHourlyForecast(long time);

    @Insert
    public abstract void addHourlyForecast(List<HourlyForecast> hourlyForecast);

    @Query("SELECT * FROM DailyForecast WHERE time >= :time")
    public abstract List<DailyForecast> getDailyForecast(long time);

    @Insert
    public abstract void addDailyForecast(List<DailyForecast> dailyForecast);

}
