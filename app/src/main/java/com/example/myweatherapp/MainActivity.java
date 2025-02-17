// MainActivity.java
package com.example.myweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherapp.Adapter.DailyWeather;
import com.example.myweatherapp.Adapter.DailyWeatherAdapter;
import com.example.myweatherapp.bean.NowWeather;
import com.example.myweatherapp.bean.SevendayBean;
import com.example.myweatherapp.josn.JsonNow;
import com.example.myweatherapp.josn.JsonUtil;
import com.example.myweatherapp.util.NetUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textview_weather_condition, textview_date_time, textview_temperature,
            textview_temperature_range, textview_humidity_value, textview_wind_speed_value,
            textview_rain_probability_value;
    private ImageView imageview_weather_icon;
    private RecyclerView recyclerview_forecast;
    private DailyWeatherAdapter dailyWeatherAdapter;

    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0) {
                String weatherJson = (String) msg.obj;
                //使用gson来解析数据
                NowWeather nowWeather=new Gson().fromJson(weatherJson,NowWeather.class);

                String currentTemperature=nowWeather.getNow().getTemp();
                String currentHumidity=nowWeather.getNow().getHumidity();
                String currentWindspeed=nowWeather.getNow().getWindSpeed();
                String currentText=nowWeather.getNow().getText();
                String currentWind=nowWeather.getNow().getWindDir();
                String currentIcon=nowWeather.getNow().getIcon();
                int iconResId = getIconResId(currentIcon);
                imageview_weather_icon.setImageResource(iconResId);
                textview_temperature.setText(currentTemperature + "°C");
                textview_humidity_value.setText(currentHumidity+"%");
                textview_wind_speed_value.setText(currentWindspeed+"KM/H");
                textview_rain_probability_value.setText(currentWind);
                textview_weather_condition.setText(currentText);
                Log.d("TAG", "温度更新：" + currentTemperature);
                // 这里可以添加其他天气信息的更新
            }
        }
    };

    private final Handler sevenDayHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0) {
                String weatherJson = (String) msg.obj;
                Log.d("HAPPY", "七天天气数据：" + weatherJson);
                SevendayBean sevendayBean=new Gson().fromJson(weatherJson,SevendayBean.class);
                String max=sevendayBean.getDaily().get(0).getTempMax();
                String min=sevendayBean.getDaily().get(0).getTempMin();
                textview_temperature_range.setText("最高温"+max+"°C"+" 最低温"+min+"°C");

                List<DailyWeather> weatherDataList = parseSevenDayWeather(weatherJson);
                dailyWeatherAdapter.updateData(weatherDataList);
                Log.d("HAPPY", "七天天气更新");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        initRecyclerView();
        String cityId = getIntent().getStringExtra("cityId");
        if (cityId == null) {
            // 设置默认城市 ID 为 北京（101010100）
            cityId = "101010100";
        }

        // 根据 cityId 更新天气数据
        fetchWeatherData(cityId);
        fetchNowWeatherData(cityId);
    }

    private void initView() {
        textview_weather_condition = findViewById(R.id.textview_weather_condition);
        imageview_weather_icon = findViewById(R.id.imageview_weather_icon);
        textview_date_time = findViewById(R.id.textview_date_time);
        textview_temperature = findViewById(R.id.textview_temperature);
        textview_temperature_range = findViewById(R.id.textview_temperature_range);
        textview_humidity_value = findViewById(R.id.textview_humidity_value);
        textview_wind_speed_value = findViewById(R.id.textview_wind_speed_value);
        textview_rain_probability_value = findViewById(R.id.textview_rain_probability_value);

    }

    private void initRecyclerView() {
        recyclerview_forecast = findViewById(R.id.recyclerview_forecast);
        recyclerview_forecast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // 初始化适配器
        dailyWeatherAdapter = new DailyWeatherAdapter(new ArrayList<>());
        recyclerview_forecast.setAdapter(dailyWeatherAdapter);
    }

    private void initEvent() {
        // 时间更新任务（1秒一次）
        final Runnable timeUpdater = new Runnable() {
            @Override
            public void run() {
                updateTimeDisplay();
                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.post(timeUpdater);

        // 当前天气更新任务（60分钟一次）
        final Runnable nowWeatherUpdater = new Runnable() {
            @Override
            public void run() {
                String cityId = getIntent().getStringExtra("cityId");
                if (cityId == null) {
                    // 设置默认城市
                    cityId = "101010100";
                }
                fetchWeatherData(cityId);
                mHandler.postDelayed(this, 3600000);
            }
        };
        mHandler.post(nowWeatherUpdater);

        // 七天天气更新任务（60分钟一次）
        final Runnable sevenDayWeatherUpdater = new Runnable() {
            @Override
            public void run() {
                String cityId = getIntent().getStringExtra("cityId");
                if (cityId == null) {
                    // 设置默认城市
                    cityId = "101010100";
                }
                fetchNowWeatherData(cityId);
                sevenDayHandler.postDelayed(this, 3600000);
            }
        };
        sevenDayHandler.post(sevenDayWeatherUpdater);


    }

    private void updateTimeDisplay() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String dayInChinese = convertDayToChinese(calendar.get(Calendar.DAY_OF_WEEK));

        String timeText = String.format("星期%s  %d月%d日| %02d:%02d", dayInChinese, month, day, hour, minute);
        textview_date_time.setText(timeText);
    }

    private String convertDayToChinese(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:    return "日";
            case Calendar.MONDAY:    return "一";
            case Calendar.TUESDAY:   return "二";
            case Calendar.WEDNESDAY: return "三";
            case Calendar.THURSDAY:  return "四";
            case Calendar.FRIDAY:    return "五";
            case Calendar.SATURDAY:  return "六";
            default:                 return "";
        }
    }

    private void fetchWeatherData(String cityId) {
        new Thread(() -> {
            String url = "https://devapi.qweather.com/v7/weather/now?location=" + cityId + "&key=553c7d8605c544e6ba68404ace22873d";
            String weatherJson = NetUtil.doGet(url);
            Message msg = mHandler.obtainMessage(0, weatherJson);
            mHandler.sendMessage(msg);
        }).start();
    }

    private void fetchNowWeatherData(String cityId) {
        new Thread(() -> {
            String url = "https://devapi.qweather.com/v7/weather/7d?location=" + cityId + "&key=553c7d8605c544e6ba68404ace22873d";
            String weatherJson = NetUtil.doGet(url);
            Message msg = sevenDayHandler.obtainMessage(0, weatherJson);
            sevenDayHandler.sendMessage(msg);
        }).start();
    }

    private List<DailyWeather> parseSevenDayWeather(String jsonStr) {
        List<DailyWeather> weatherDataList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            if (!"200".equals(jsonObject.optString("code"))) {
                Log.e("HAPPY", "API请求失败，code：" + jsonObject.optString("code"));
                return weatherDataList;
            }
            JSONArray dailyArray = jsonObject.getJSONArray("daily");
            for (int i = 0; i < dailyArray.length(); i++) {
                String textDay = JsonUtil.getWeatherField(jsonStr, "fxDate", i);
                String tempMax = JsonUtil.getWeatherField(jsonStr, "tempMax", i);
                String tempMin = JsonUtil.getWeatherField(jsonStr, "tempMin", i);
                String icon = JsonUtil.getWeatherField(jsonStr, "iconDay", i);
                String tempRange = tempMin + "°C - " + tempMax + "°C";
                int iconResId = getIconResId(icon);
                weatherDataList.add(new DailyWeather(textDay, iconResId, tempRange));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("HAPPY", "解析 JSON 失败");
        }
        return weatherDataList;
    }

    private int getIconResId(String iconCode) {
        // 根据 iconCode 返回对应的资源 ID
        switch (iconCode) {
            case "100": return R.drawable.sunny;
            case "101": return R.drawable.cloudy;
            case "102": return R.drawable.cloudy;
            case "103": return R.drawable.cloudy;
            case "305": return R.drawable.showers;
            case "306": return R.drawable.showers;
            case "307": return R.drawable.showers;
            case "308": return R.drawable.showers;
            case "309": return R.drawable.showers;
            case "310": return R.drawable.showers;
            // 添加其他图标映射
            default: return R.drawable.sunny;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        sevenDayHandler.removeCallbacksAndMessages(null);
    }


    public void openNewActivity(View view) {
        Intent intent = new Intent(this, activity_CityChoice.class);
        startActivity(intent);
    }
}
