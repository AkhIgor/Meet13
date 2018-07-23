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
class Daily implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long DailyID;
    @ColumnInfo(name = "dailySummary")
    private String Summary;
    @ColumnInfo(name = "dailyIcon")
    private String Icon;

    @Ignore
    private List<DailyForecast> Forecast;

    public Daily() {
    }

    public long getDailyID() {
        return DailyID;
    }

    public void setDailyID(long id) {
        this.DailyID = id;
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

    public List<DailyForecast> getForecast() {
        return Forecast;
    }

    public void setForecast(List<DailyForecast> forecast) {
        this.Forecast = forecast;
    }

    protected Daily(Parcel in) {
        Summary = in.readString();
        Icon = in.readString();
        Forecast = in.createTypedArrayList(DailyForecast.CREATOR);
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

    public static final Creator<Daily> CREATOR = new Creator<Daily>() {
        @Override
        public Daily createFromParcel(Parcel in) {
            return new Daily(in);
        }

        @Override
        public Daily[] newArray(int size) {
            return new Daily[size];
        }
    };
}
