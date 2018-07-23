package com.example.meet13;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Игорь on 15.07.2018.
 */

public class Formats {

    private static final int dateTimeConst = 1000;
    private static final int humidityConst = 100;
    private static final double pressureConst = 0.749;

    public Formats() {
    }

    public static String dateFormat(long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd MMM yyyy", Locale.getDefault());
        return simpleDateFormat.format(new Date(date * dateTimeConst));
    }

    public static String formatHummidity(double humidity) {
        int format = (int) humidity * humidityConst;
        return String.format(Locale.getDefault(), "%d%%", format);
    }

    public static String formatPressure(double pressure) {
        int format = (int) (pressure * pressureConst);
        return String.format(Locale.getDefault(), "%d", format);
    }

    public static String timeFormat(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return simpleDateFormat.format(new Date(time * dateTimeConst));
    }

    public static String tempFormat(double temperature) {
        int temp = (int) temperature;

        if (temp > 0)
            return String.format("+%s\u00b0", String.valueOf(temp));
        if (temp < 0)
            return String.format("-%s\u00b0", String.valueOf(temp));

        return String.format(" %s\u00b0", String.valueOf(temp));
    }

    public static String speedFormat(double speed) {
        int format = (int) speed;
        return String.format(Locale.getDefault(), "%.1f", speed);
    }

    public static int setImage(String name) {
        int image;

        switch (name) {
            case "clear-day": {
                image = R.drawable.sun;
                break;
            }
            case "clear-night": {
                image = R.drawable.moon;
                break;
            }
            case "rain": {
                image = R.drawable.cloudy_rain;
                break;
            }
            case "sleet": {
                image = R.drawable.rain;
                break;
            }
            case "snow": {
                image = R.drawable.cloudy_snow;
                break;
            }
            case "wind": {
                image = R.drawable.cold_wind;
                break;
            }
            case "fog": {
                image = R.drawable.cloudy;
                break;
            }
            case "cloudy": {
                image = R.drawable.cloudy;
                break;
            }
            case "partly-cloudy-day": {
                image = R.drawable.cloudy;
                break;
            }
            case "partly-cloudy-night": {
                image = R.drawable.cloudy;
                break;
            }
            default: {
                image = R.drawable.sun;
                break;
            }
        }
        return image;
    }
}
