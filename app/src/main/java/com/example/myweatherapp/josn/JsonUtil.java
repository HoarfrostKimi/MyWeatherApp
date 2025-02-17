package com.example.myweatherapp.josn;

import com.example.myweatherapp.Adapter.DailyWeather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
    public static String getWeatherField(String jsonStr, String fieldName, int index) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);

            // 检查 API 是否返回错误
            if (!jsonObject.getString("code").equals("200")) {
                return "错误: API 请求失败";
            }

            JSONArray dailyArray = jsonObject.getJSONArray("daily"); // 获取 "daily" 数组
            JSONObject dayObject = dailyArray.getJSONObject(index); // 获取指定索引的天气数据

            // **动态获取任意字段**
            return dayObject.optString(fieldName, "未知");

        } catch (JSONException e) {
            e.printStackTrace();
            return "解析 JSON 失败";
        }
    }

}