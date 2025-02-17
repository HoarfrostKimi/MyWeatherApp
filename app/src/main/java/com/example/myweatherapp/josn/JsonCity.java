package com.example.myweatherapp.josn;

import com.example.myweatherapp.City;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonCity {
    public static List<City> getAllCities(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);

            // 检查 API 是否返回错误
            if (!jsonObject.getString("code").equals("200")) {
                throw new JSONException("错误: API 请求失败");
            }

            JSONArray locationArray = jsonObject.getJSONArray("location"); // 获取 "location" 数组

            List<City> cityList = new ArrayList<>();
            for (int i = 0; i < locationArray.length(); i++) {
                JSONObject cityObject = locationArray.getJSONObject(i);
                String name = cityObject.optString("name");
                String province = cityObject.optString("adm2");
                String country = cityObject.optString("country");
                String id = cityObject.optString("id");

                City city = new City(name, province, country, id);
                cityList.add(city);
            }

            return cityList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
