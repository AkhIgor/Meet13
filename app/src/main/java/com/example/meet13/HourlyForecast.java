package com.example.meet13;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Игорь on 15.07.2018.
 */

@Entity
class HourlyForecast implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long time;
    private String icon;
    private double temperature;

    public HourlyForecast() {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    protected HourlyForecast(Parcel in) {
        id = in.readLong();
        time = in.readLong();
        icon = in.readString();
        temperature = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(time);
        dest.writeString(icon);
        dest.writeDouble(temperature);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HourlyForecast> CREATOR = new Creator<HourlyForecast>() {
        @Override
        public HourlyForecast createFromParcel(Parcel in) {
            return new HourlyForecast(in);
        }

        @Override
        public HourlyForecast[] newArray(int size) {
            return new HourlyForecast[size];
        }
    };
}
