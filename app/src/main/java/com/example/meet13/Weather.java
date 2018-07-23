package com.example.meet13;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Игорь on 14.07.2018.
 */

@Entity
class Weather implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    private long ID;
    private double Latitude;
    private double Longitude;
    private String Timezone;
    @Embedded
    private Daily Daily;
    @Embedded
    private Hourly Hourly;

    public Weather() {
    }

    public Weather(long id, double latitude, double longitude, String timezone) {
        this.ID = id;
        this.Latitude = latitude;
        this.Longitude = longitude;
        this.Timezone = timezone;
    }

    protected Weather(Parcel in) {
        Latitude = in.readDouble();
        Longitude = in.readDouble();
        Timezone = in.readString();
        Daily = in.readParcelable(Daily.class.getClassLoader());
        Hourly = in.readParcelable(Hourly.class.getClassLoader());
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(Latitude);
        dest.writeDouble(Longitude);
        dest.writeString(Timezone);
        dest.writeParcelable(Daily, flags);
        dest.writeParcelable(Hourly, flags);
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        this.Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        this.Longitude = longitude;
    }

    public String getTimezone() {
        return Timezone;
    }

    public void setTimezone(String timezone) {
        this.Timezone = timezone;
    }

    public Daily getDaily() {
        return Daily;
    }

    public void setDaily(Daily daily) {
        this.Daily = daily;
    }

    public Hourly getHourly() {
        return Hourly;
    }

    public void setHourly(Hourly hourly) {
        this.Hourly = hourly;
    }
}
