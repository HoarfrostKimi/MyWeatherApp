package com.example.myweatherapp;

public class City {
    String name;
    String province;
    String country;
    String id;

    public City(String name, String province, String country, String id) {
        this.name = name;
        this.province = province;
        this.country = country;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    public String getCountry() {
        return country;
    }

    public String getId() {
        return id;
    }
    @Override
    public String toString() {
        return "    "+name + "-" + province + "-" + country ;
    }
}
