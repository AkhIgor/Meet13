package com.example.meet13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    private TextView dateText;
    private ImageView icon;
    private TextView dayTemp;
    private TextView nigthTemp;
    private TextView description;
    private TextView sunrise;
    private TextView sunset;
    private TextView windSpeed;
    private TextView pressure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        icon = (ImageView) findViewById(R.id.weatherIcon);
        dateText = (TextView) findViewById(R.id.dateText);
        dayTemp = (TextView) findViewById(R.id.dayTemp);
        nigthTemp = (TextView) findViewById(R.id.nightTemp);
        description = (TextView) findViewById(R.id.description);

        sunrise = (TextView) findViewById(R.id.sunriseText);
        sunset = (TextView) findViewById(R.id.sunsetText);
        windSpeed = (TextView) findViewById(R.id.windspeedText);
        pressure = (TextView) findViewById(R.id.pressureText);

        DailyForecast dailyForecast = getIntent().getParcelableExtra(MainActivity.WEATHER_INFO);

        icon.setImageResource(Formats.setImage(dailyForecast.getIcon()));
        dateText.setText(Formats.dateFormat(dailyForecast.getTime()));
        dayTemp.setText(Formats.tempFormat(dailyForecast.getTemperatureHigh()));
        nigthTemp.setText(Formats.tempFormat(dailyForecast.getTemperatureLow()));
        description.setText(dailyForecast.getSummary());

        sunrise.setText(Formats.timeFormat(dailyForecast.getSunriseTime()));
        sunset.setText(Formats.timeFormat(dailyForecast.getSunsetTime()));
        windSpeed.setText(Formats.speedFormat(dailyForecast.getWindSpeed()));
        pressure.setText(Formats.formatPressure(dailyForecast.getPressure()));
    }
}
