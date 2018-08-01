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
    private long id;
    private double latitude;
    private double longitude;
    private String timezone;
    @Embedded
    private Daily daily;
    @Embedded
    private Hourly hourly;

    public Weather() {
    }

    public Weather(long id, double latitude, double longitude, String timezone) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
    }

    protected Weather(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        timezone = in.readString();
        daily = in.readParcelable(Daily.class.getClassLoader());
        hourly = in.readParcelable(Hourly.class.getClassLoader());
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
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(timezone);
        dest.writeParcelable(daily, flags);
        dest.writeParcelable(hourly, flags);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }
}
