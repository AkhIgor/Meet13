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
    public DailyForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_list_item, parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DailyForecastAdapter.ViewHolder holder, int position) {
        final DailyForecast forecast = Forecast.get(position);

        holder.Icon.setImageResource(Formats.setImage(forecast.getIcon()));
        holder.DayTemp.setText(Formats.tempFormat(forecast.getDayTemperature()));
        holder.NigthTemp.setText(Formats.tempFormat(forecast.getNigthTemperature()));
        holder.Date.setText(Formats.dateFormat(forecast.getTime()));
        holder.Description.setText(forecast.getSummary());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCalBack.onItemClick(forecast);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Forecast.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView Icon;
        private TextView DayTemp;
        private TextView NigthTemp;
        private TextView Date;
        private TextView Description;

        public ViewHolder(View itemView) {
            super(itemView);

            Icon = (ImageView) itemView.findViewById(R.id.icon);
            DayTemp = (TextView) itemView.findViewById(R.id.temp);
            NigthTemp = (TextView) itemView.findViewById(R.id.nigthTemp);
            Date = (TextView) itemView.findViewById(R.id.date);
            Description = (TextView) itemView.findViewById(R.id.description);
        }
    }

    public interface MyCallBack {
        void onItemClick(DailyForecast dailyForecast);
    }
}
