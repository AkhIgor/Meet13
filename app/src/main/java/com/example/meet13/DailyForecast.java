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
    private long ID;
    private long Time;
    private String Summary;
    private String Icon;
    private long SunriseTime;
    private long SunsetTime;
    private double DayTemperature;
    private double NigthTemperature;
    private double Humidity;
    private double Pressure;
    private double WindSpeed;

    public DailyForecast() {
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

    public long getSunriseTime() {
        return SunriseTime;
    }

    public void setSunriseTime(long sunriseTime) {
        this.SunriseTime = sunriseTime;
    }

    public long getSunsetTime() {
        return SunsetTime;
    }

    public void setSunsetTime(long sunsetTime) {
        this.SunsetTime = sunsetTime;
    }

    public double getDayTemperature() {
        return DayTemperature;
    }

    public void setDayTemperature(double dayTemperature) {
        this.DayTemperature = dayTemperature;
    }

    public double getNigthTemperature() {
        return NigthTemperature;
    }

    public void setNigthTemperature(double nigthTemperature) {
        this.NigthTemperature = nigthTemperature;
    }

    public double getHumidity() {
        return Humidity;
    }

    public void setHumidity(double humidity) {
        this.Humidity = humidity;
    }

    public double getPressure() {
        return Pressure;
    }

    public void setPressure(double pressure) {
        this.Pressure = pressure;
    }

    public double getWindSpeed() {
        return WindSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.WindSpeed = windSpeed;
    }
    
    protected DailyForecast(Parcel in) {
        Time = in.readLong();
        Summary = in.readString();
        Icon = in.readString();
        SunriseTime = in.readLong();
        SunsetTime = in.readLong();
        DayTemperature = in.readDouble();
        NigthTemperature = in.readDouble();
        Humidity = in.readDouble();
        Pressure = in.readDouble();
        WindSpeed = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(Time);
        dest.writeString(Summary);
        dest.writeString(Icon);
        dest.writeLong(SunriseTime);
        dest.writeLong(SunsetTime);
        dest.writeDouble(DayTemperature);
        dest.writeDouble(NigthTemperature);
        dest.writeDouble(Humidity);
        dest.writeDouble(Pressure);
        dest.writeDouble(WindSpeed);
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
