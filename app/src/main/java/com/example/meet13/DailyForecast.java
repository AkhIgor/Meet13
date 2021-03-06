package com.example.meet13;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Игорь on 15.07.2018.
 */
@Entity
class DailyForecast implements Parcelable {
    
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long time;
    private String summary;
    private String icon;
    private long sunriseTime;
    private long sunsetTime;
    private double temperatureHigh;
    private double temperatureLow;
    private double humidity;
    private double pressure;
    private double windSpeed;

    public DailyForecast() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(long sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public long getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(long sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public double getTemperatureHigh() {
        return temperatureHigh;
    }

    public void setTemperatureHigh(double dayTemperature) {
        this.temperatureHigh = dayTemperature;
    }

    public double getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(double nigthTemperature) {
        this.temperatureLow = nigthTemperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
    
    protected DailyForecast(Parcel in) {
        time = in.readLong();
        summary = in.readString();
        icon = in.readString();
        sunriseTime = in.readLong();
        sunsetTime = in.readLong();
        temperatureHigh = in.readDouble();
        temperatureLow = in.readDouble();
        humidity = in.readDouble();
        pressure = in.readDouble();
        windSpeed = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(time);
        dest.writeString(summary);
        dest.writeString(icon);
        dest.writeLong(sunriseTime);
        dest.writeLong(sunsetTime);
        dest.writeDouble(temperatureHigh);
        dest.writeDouble(temperatureLow);
        dest.writeDouble(humidity);
        dest.writeDouble(pressure);
        dest.writeDouble(windSpeed);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DailyForecast> CREATOR = new Creator<DailyForecast>() {
        @Override
        public DailyForecast createFromParcel(Parcel in) {
            return new DailyForecast(in);
        }

        @Override
        public DailyForecast[] newArray(int size) {
            return new DailyForecast[size];
        }
    };
}
