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

public class DailyForecastAdapter extends RecyclerView.Adapter<DailyForecastAdapter.ViewHolder> {

    private List<DailyForecast> Forecast;
    private MyCallBack myCalBack;
    private Context Context;

    public DailyForecastAdapter(List<DailyForecast> forecast, Context context, MyCallBack myCalBack) {
        this.Forecast = forecast;
        this.Context = context;
        this.myCalBack = myCalBack;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(Context).inflate(R.layout.daily_list_item, parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DailyForecast forecast = Forecast.get(position);

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
        Forecast.clear();
        Forecast.addAll(data);
    }

    @Override
    public int getItemCount() {
        return Forecast.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView Icon;
        private TextView DayTemp;
        private TextView NightTemp;
        private TextView Date;
        private TextView Description;

        public ViewHolder(View itemView) {
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
}