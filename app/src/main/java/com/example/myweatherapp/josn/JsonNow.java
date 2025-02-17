// JsonNow.java
package com.example.myweatherapp.josn;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonNow {
    /**
     * 从 now JSON 数据中解析当前温度
     * @param jsonStr now 接口返回的 JSON 字符串
     * @return 当前温度，如果解析失败则返回错误信息
     */
    public static String getCurrentTemperature(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            // 检查 API 是否返回成功
            if (!"200".equals(jsonObject.optString("code"))) {
                return "错误: API请求失败，code：" + jsonObject.optString("code");
            }
            // 获取 now 对象
            JSONObject nowObject = jsonObject.optJSONObject("now");
            if (nowObject == null) {
                return "错误: 当前天气数据不存在";
            }
            // 返回当前温度
            return nowObject.optString("temp", "未知");
        } catch (JSONException e) {
            e.printStackTrace();
            return "解析 JSON 失败";
        }
    }
}

