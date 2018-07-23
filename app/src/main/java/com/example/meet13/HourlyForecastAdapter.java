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

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder> {

    private List<HourlyForecast> Forecast;
    private Context Context;

    public HourlyForecastAdapter(List<HourlyForecast> forecast, Context context) {
        this.Forecast = forecast;
        this.Context = context;
    }

    @Override
    public HourlyForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HourlyForecastAdapter.ViewHolder holder, int position) {
        HourlyForecast forecast = Forecast.get(position);

        holder.HourText.setText(Formats.timeFormat(forecast.getTime()));
        holder.Temp.setText(Formats.tempFormat(forecast.getTemperature()));
        holder.Icon.setImageResource(Formats.setImage(forecast.getIcon()));
    }

    @Override
    public int getItemCount() {
        return Forecast.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView HourText;
        TextView Temp;
        ImageView Icon;

        public ViewHolder(View itemView) {
            super(itemView);

            HourText = (TextView) itemView.findViewById(R.id.hourText);
            Temp = (TextView) itemView.findViewById(R.id.temp);
            Icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}
