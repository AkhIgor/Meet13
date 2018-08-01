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
    private long dailyId;
    @ColumnInfo(name = "dailySummary")
    private String summary;
    @ColumnInfo(name = "dailyIcon")
    private String icon;

    @Ignore
    private List<DailyForecast> data;

    public Daily() {
    }

    public long getDailyId() {
        return dailyId;
    }

    public void setDailyId(long id) {
        this.dailyId = id;
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

    public List<DailyForecast> getData() {
        return data;
    }

    public void setData(List<DailyForecast> forecast) {
        this.data = forecast;
    }

    protected Daily(Parcel in) {
        summary = in.readString();
        icon = in.readString();
        data = in.createTypedArrayList(DailyForecast.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(summary);
        dest.writeString(icon);
        dest.writeTypedList(data);
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
