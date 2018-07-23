package com.example.meet13;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.concurrent.TimeUnit;

/**
 * Created by Игорь on 15.07.2018.
 */

@Entity
class HourlyForecast implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long ID;
    private long Time;
    private String Icon;
    private double Temperature;

    public HourlyForecast() {
    }

    public long getID() {
        return ID;
    }

    public void setID(long id) {
        this.ID = id;
    }

    public long getTime() {
        return Time;
    }

    public void setTime(long time) {
        this.Time = time;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        this.Icon = icon;
    }

    public double getTemperature() {
        return Temperature;
    }

    public void setTemperature(double temperature) {
        this.Temperature = temperature;
    }

    protected HourlyForecast(Parcel in) {
        ID = in.readLong();
        Time = in.readLong();
        Icon = in.readString();
        Temperature = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(ID);
        dest.writeLong(Time);
        dest.writeString(Icon);
        dest.writeDouble(Temperature);
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
