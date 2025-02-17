package com.example.myweatherapp.database;

public class DatabaseBean {


    private int feelslike;
    private int temp;
    private int id;
    private String city;
    private String weather;

    public DatabaseBean(int feelslike, int temp, int id, String city, String weather) {
        this.feelslike = feelslike;
        this.temp = temp;
        this.id = id;
        this.city = city;
        this.weather = weather;
    }

    public DatabaseBean() {
    }

    public int getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(int feelslike) {
        this.feelslike = feelslike;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
