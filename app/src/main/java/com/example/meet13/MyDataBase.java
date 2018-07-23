package com.example.meet13;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.meet13.WeatherDao;
import com.example.meet13.Daily;
import com.example.meet13.DailyForecast;
import com.example.meet13.HourlyForecast;
import com.example.meet13.Weather;

/**
 * Created by Игорь on 15.07.2018.
 */

@Database(entities = {Weather.class, Daily.class, DailyForecast.class, Hourly.class, HourlyForecast.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {

    private static MyDataBase Instance;

    public abstract WeatherDao weatherDao();

    public static MyDataBase getInstance(Context context) {
        if(Instance == null) {
            synchronized (Weather.class) {
                if(Instance == null) {
                    Instance = Room.databaseBuilder(context, MyDataBase.class, "forecast_db").build();
                }
            }
        }
        return Instance;
    }

    public static void destroyInstance() {
        Instance = null;
    }
}
