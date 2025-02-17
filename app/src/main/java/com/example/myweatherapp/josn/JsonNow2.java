package com.example.myweatherapp.josn;

import com.example.myweatherapp.Adapter.Now2;
import com.example.myweatherapp.City;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonNow2 {
    public static List<Now2> JsonNow2(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);

            // 检查 API 是否返回错误
            if (!jsonObject.getString("code").equals("200")) {
                throw new JSONException("错误: API 请求失败");
            }

            JSONArray locationArray = jsonObject.getJSONArray("now"); // 获取 "location" 数组

            List<Now2> Now2List = new ArrayList<>();
            String weather = jsonObject.optString("text");
            String feelslike = jsonObject.optString("feelslike");
            String temp = jsonObject.optString("temp");
            Now2 Now2 = new Now2(weather,feelslike,temp);
            Now2List.add(Now2);


            return Now2List;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
