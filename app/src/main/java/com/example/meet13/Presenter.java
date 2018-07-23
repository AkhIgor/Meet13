package com.example.meet13;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import java.util.List;

/**
 * Created by Игорь on 16.07.2018.
 */

public class Presenter implements MainManager.Presenter {

    private MainManager.View View;
    private MainManager.Model Model;

    public Presenter(MainManager.View view, MainManager.Model model) {
        this.View = view;
        this.Model = model;
    }

    @Override
    public void initializeData(ConnectivityManager connectivityManager) {
        boolean Connection = false;

        View.showProgressBar();

        NetworkInfo networkInfo = null;

        if(connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo!= null)
                Connection = networkInfo.isConnectedOrConnecting();
        }

        if(Connection)
            View.startService();
        else
            getData();
    }

    private void getData() {
        View.hideProgressBar();

        Weather weather = Model.getWeather();
        List<DailyForecast> forecasts = weather.getDaily().getForecast();
        View.hideProgressBar();

        if(forecasts != null)
            View.showWeather(weather);
        else
            View.showMessage();
    }
}
