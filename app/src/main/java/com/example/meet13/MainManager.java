package com.example.meet13;

import android.net.ConnectivityManager;

/**
 * Created by Игорь on 16.07.2018.
 */

public interface MainManager {

    interface View {
        void showWeather(Weather weather);
        void showProgressBar();
        void hideProgressBar();
        void showMessage();
        void startService();
    }

    interface Model {
        Weather getWeather();
    }

    interface Presenter {
        void initializeData(ConnectivityManager connectivityManager);
    }
}
