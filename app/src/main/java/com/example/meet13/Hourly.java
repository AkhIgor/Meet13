package com.example.meet13;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Игорь on 14.07.2018.
 */
@Entity
class Hourly implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long HourlyID;
    @ColumnInfo(name = "hourlySummary")
    private String Summary;
    @ColumnInfo(name = "hourlyIcon")
    private String Icon;

    @Ignore
    private List<HourlyForecast> Forecast;

    public Hourly() {
    }

    public long getHourlyID() {
        return HourlyID;
    }

    public void setHourlyID(long id) {
        this.HourlyID = id;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        this.Summary = summary;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        this.Icon = icon;
    }

    public List<HourlyForecast> getForecast() {
        return Forecast;
    }

    public void setForecast(List<HourlyForecast> forecast) {
        this.Forecast = forecast;
    }

    protected Hourly(Parcel in) {
        Summary = in.readString();
        Icon = in.readString();
        Forecast = in.createTypedArrayList(HourlyForecast.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Summary);
        dest.writeString(Icon);
        dest.writeTypedList(Forecast);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Hourly> CREATOR = new Creator<Hourly>() {
        @Override
        public Hourly createFromParcel(Parcel in) {
            return new Hourly(in);
        }

        @Override
        public Hourly[] newArray(int size) {
            return new Hourly[size];
        }
    };
}
