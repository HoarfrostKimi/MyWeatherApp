package com.example.myweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherapp.Adapter.ItemTouchHelperAdapter;
import com.example.myweatherapp.Adapter.SimpleItemTouchHelperCallback;
import com.example.myweatherapp.Adapter.WeatherItem;
import com.example.myweatherapp.bean.NowWeather;
import com.example.myweatherapp.util.NetUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class activity_CityChoice extends AppCompatActivity {
    private ItemTouchHelperAdapter adapter; // 声明 adapter 变量
    private RecyclerView recyclerView;
    private List<WeatherItem> items = new ArrayList<>();
    private int apiIndex = 0;
    private final int TOTAL_APIS = 5; // 根据实际接口数量调整

    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0) {
                int currentApiIndex = msg.arg1; // 使用传递的 apiIndex
                String weatherJson = (String) msg.obj;
                Log.d("CityChoice", "Received JSON for index " + currentApiIndex + ": " + weatherJson);

                Gson gson = new Gson();
                NowWeather nowWeather = gson.fromJson(weatherJson, NowWeather.class);

                if (nowWeather != null && nowWeather.getNow() != null) {
                    String weatherInfo = nowWeather.getNow().getText() + " 体感" + nowWeather.getNow().getFeelsLike() + "℃";
                    String temperature = nowWeather.getNow().getTemp() + "℃";

                    String cityName = getCityName(currentApiIndex);
                    if (!cityName.isEmpty()) {
                        items.add(new WeatherItem(cityName, weatherInfo, temperature, generateCityId(cityName)));
                        Log.d("CityChoice", "Added city: " + cityName + ", Weather Info: " + weatherInfo + ", Temperature: " + temperature);
                    }
                } else {
                    Log.e("CityChoice", "Failed to parse JSON for index " + currentApiIndex + ": " + weatherJson);
                }

                // 请求下一个 API
                apiIndex++; // 在处理完当前数据后递增 apiIndex
                fetchNextCitiesData();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_choice);

        // 初始化 RecyclerView
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 设置适配器
        adapter = new ItemTouchHelperAdapter(items);
        recyclerView.setAdapter(adapter);

        // 设置 ItemTouchHelper
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        // 设置 RecyclerView 的点击事件监听器
        adapter.setOnItemClickListener(position -> {
            WeatherItem selectedCity = items.get(position);  // 获取选中的城市
            Intent intent = new Intent(activity_CityChoice.this, MainActivity.class);
            intent.putExtra("cityId", selectedCity.getCityId()); // 传递 cityId
            startActivity(intent);
        });

        // 获取城市数据
        fetchNextCitiesData();
    }

    private void fetchNextCitiesData() {
        if (apiIndex < TOTAL_APIS) {
            String[] apiUrls = {
                    "https://devapi.qweather.com/v7/weather/now?location=101250101&key=553c7d8605c544e6ba68404ace22873d", // 长沙市
                    "https://devapi.qweather.com/v7/weather/now?location=101020100&key=553c7d8605c544e6ba68404ace22873d", // 上海市
                    "https://devapi.qweather.com/v7/weather/now?location=101010100&key=553c7d8605c544e6ba68404ace22873d", // 北京市
                    "https://devapi.qweather.com/v7/weather/now?location=101280101&key=553c7d8605c544e6ba68404ace22873d", // 广州市
                    "https://devapi.qweather.com/v7/weather/now?location=101280601&key=553c7d8605c544e6ba68404ace22873d"  // 深圳市
            };

            String url = apiUrls[apiIndex];
            new Thread(() -> {
                String weatherJson = NetUtil.doGet(url);
                if (weatherJson != null) {
                    Message msg = mHandler.obtainMessage(0, weatherJson);
                    msg.arg1 = apiIndex; // 传递当前的 apiIndex
                    mHandler.sendMessage(msg);
                } else {
                    Log.e("CityChoice", "Failed to fetch data from URL: " + url);
                    // 不递增 apiIndex，重试当前请求
                    fetchNextCitiesData();
                }
            }).start();
        } else {
            // 所有数据已获取，通知适配器数据已更改
            runOnUiThread(() -> adapter.notifyDataSetChanged());
        }
    }

    private String getCityName(int index) {
        switch (index) {
            case 0:
                return "长沙市";
            case 1:
                return "上海市";
            case 2:
                return "北京市";
            case 3:
                return "广州市";
            case 4:
                return "深圳市";
            default:
                return "";
        }
    }

    private String generateCityId(String cityName) {
        // 这里可以根据城市名生成唯一的 cityId，例如：
        switch (cityName) {
            case "长沙市":
                return "101250101";
            case "上海市":
                return "101020100";
            case "北京市":
                return "101010100";
            case "广州市":
                return "101280101";
            case "深圳市":
                return "101280601";
            default:
                return "";
        }
    }

    public void openMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openChoiceActivity(View view) {
        Intent intent = new Intent(this, SearchCity.class);
        startActivity(intent);
    }
}
