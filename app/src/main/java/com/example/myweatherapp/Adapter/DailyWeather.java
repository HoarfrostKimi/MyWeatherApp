package com.example.myweatherapp.Adapter;

public class DailyWeather {
    private String date;  // 日期
    private int weatherIconResId;  // 天气图标资源 ID
    private String temperatureRange;  // 温度范围（最低温 - 最高温）

    public DailyWeather(String date, int weatherIconResId, String temperatureRange) {
        this.date = date;
        this.weatherIconResId = weatherIconResId;
        this.temperatureRange = temperatureRange;
    }

    public String getDate() {
        return date;
    }

    public int getWeatherIconResId() {
        return weatherIconResId;
    }

    public String getTemperatureRange() {
        return temperatureRange;
    }
}
