package com.example.myweatherapp.Adapter;

public class WeatherItem {
    private String cityName;
    private String weatherInfo;
    private String temperature;
    private String cityId; // 添加 cityId 字段

    public WeatherItem(String cityName, String weatherInfo, String temperature, String cityId) {
        this.cityName = cityName;
        this.weatherInfo = weatherInfo;
        this.temperature = temperature;
        this.cityId = cityId;
    }

    public void setWeatherInfo(String weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCityName() {
        return cityName;
    }

    public String getWeatherInfo() {
        return weatherInfo;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getCityId() {
        return cityId;
    }
}
