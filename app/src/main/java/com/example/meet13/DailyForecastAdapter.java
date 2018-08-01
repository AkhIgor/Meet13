package com.example.meet13;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Игорь on 15.07.2018.
 */

public class DailyForecastAdapter extends RecyclerView.Adapter<DailyForecastAdapter.DailyViewHolder> {

    private List<DailyForecast> dailyForecast;
    private List<HourlyForecast> hourlyForecast;
    private MyCallBack myCalBack;
    private Context Context;

    public DailyForecastAdapter(List<DailyForecast> forecast, Context context, MyCallBack myCalBack) {
        this.dailyForecast = forecast;
        this.Context = context;
        this.myCalBack = myCalBack;
    }

    @Override
    public DailyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(Context).inflate(R.layout.daily_list_item, parent);
        return new DailyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DailyViewHolder holder, int position) {
        final DailyForecast forecast = dailyForecast.get(position);

        holder.Icon.setImageResource(Formats.setImage(forecast.getIcon()));
        holder.DayTemp.setText(Formats.tempFormat(forecast.getTemperatureHigh()));
        holder.NightTemp.setText(Formats.tempFormat(forecast.getTemperatureLow()));
        holder.Date.setText(Formats.dateFormat(forecast.getTime()));
        holder.Description.setText(forecast.getSummary());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCalBack.onItemClick(forecast);
            }
        });
    }

    public void addDailyData(List<DailyForecast> data){
        dailyForecast.clear();
        dailyForecast.addAll(data);
    }

    public void addHourlyData(List<HourlyForecast> data){
        hourlyForecast.clear();
        hourlyForecast.addAll(data);
    }

    @Override
    public int getItemCount() {
        return dailyForecast.size();
    }

    public class DailyViewHolder extends RecyclerView.ViewHolder {

        private ImageView Icon;
        private TextView DayTemp;
        private TextView NightTemp;
        private TextView Date;
        private TextView Description;

        public DailyViewHolder(View itemView) {
            super(itemView);

            Icon = (ImageView) itemView.findViewById(R.id.imageDaily);
            DayTemp = (TextView) itemView.findViewById(R.id.dayTemp);
            NightTemp = (TextView) itemView.findViewById(R.id.nightTemp);
            Date = (TextView) itemView.findViewById(R.id.date);
            Description = (TextView) itemView.findViewById(R.id.descriptionDaily);
        }
    }

    public interface MyCallBack {
        void onItemClick(DailyForecast dailyForecast);
    }

    public class HourlyViewHolder extends RecyclerView.ViewHolder {

        TextView HourText;
        TextView Temp;
        ImageView Icon;

        public HourlyViewHolder(View itemView) {
            super(itemView);

            HourText = (TextView) itemView.findViewById(R.id.hourText);
            Temp = (TextView) itemView.findViewById(R.id.Temper);
            Icon = (ImageView) itemView.findViewById(R.id.iconHourly);
        }
    }
}