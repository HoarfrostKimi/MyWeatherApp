package com.example.myweatherapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherapp.R;

import java.util.List;

public class DailyWeatherAdapter extends RecyclerView.Adapter<DailyWeatherAdapter.ViewHolder> {

    private List<DailyWeather> weatherDataList;

    public DailyWeatherAdapter(List<DailyWeather> weatherDataList) {
        this.weatherDataList = weatherDataList;
    }

    @NonNull
    @Override
    public DailyWeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyWeatherAdapter.ViewHolder holder, int position) {
        if (weatherDataList != null && position < weatherDataList.size()) {
            DailyWeather weatherData = weatherDataList.get(position);
            holder.textview_date.setText(weatherData.getDate());
            holder.imageview_weather_icon.setImageResource(weatherData.getWeatherIconResId());
            holder.textview_temperature.setText(weatherData.getTemperatureRange());
        }
    }

    @Override
    public int getItemCount() {
        return (weatherDataList == null) ? 0 : weatherDataList.size();
    }

    /**
     * 更新 RecyclerView 数据并刷新视图
     */
    public void updateData(List<DailyWeather> newData) {
        weatherDataList.clear();
        weatherDataList.addAll(newData);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview_date;
        ImageView imageview_weather_icon;
        TextView textview_temperature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_date = itemView.findViewById(R.id.textview_date);
            imageview_weather_icon = itemView.findViewById(R.id.imageview_weather_icon);
            textview_temperature = itemView.findViewById(R.id.textview_temperature);
        }
    }
}
