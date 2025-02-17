package com.example.myweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myweatherapp.database.CityDatabaseHelper;
import com.example.myweatherapp.josn.JsonCity;
import com.example.myweatherapp.util.NetUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchCity extends AppCompatActivity {
    private EditText searchEditText;
    private Button searchButton;
    private ListView cityListView;
    private List<City> cityList = new ArrayList<>();
    private CityDatabaseHelper dbHelper;
    private final Handler cityHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0) {
                String weatherJson = (String) msg.obj;
                cityList = JsonCity.getAllCities(weatherJson);
                updateCityList();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);

        // 初始化控件
        searchEditText = findViewById(R.id.search_edit_text);
        searchButton = findViewById(R.id.search_button);
        cityListView = findViewById(R.id.city_list_view);
        dbHelper = new CityDatabaseHelper(this);
        // 设置适配器
        ArrayAdapter<City> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityList);
        cityListView.setAdapter(adapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTerm = searchEditText.getText().toString();
                if (searchTerm.isEmpty()) {
                    Toast.makeText(SearchCity.this, "请输入城市名称", Toast.LENGTH_SHORT).show();
                } else {
                    loadCities(searchTerm);
                }
            }
        });

        // 设置列表项点击事件
        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                City selectedCity = cityList.get(position);
                dbHelper.addCity(selectedCity.getId(), selectedCity.getName());
                Toast.makeText(SearchCity.this, "选择了: " + selectedCity.getName(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(SearchCity.this,activity_CityChoice.class);
                startActivity(intent);
            }
        });
    }

    private void filterCities(String searchTerm) {
        List<City> filteredList = new ArrayList<>();
        for (City city : cityList) {
            if (city.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                filteredList.add(city);
            }
        }
        ArrayAdapter<City> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredList);
        cityListView.setAdapter(adapter);
    }

    private void loadCities(final String searchTerm) {
        new Thread(() -> {
            String weatherJson = NetUtil.doGet("https://geoapi.qweather.com/v2/city/lookup?location=" + searchTerm + "&key=553c7d8605c544e6ba68404ace22873d");
            Message msg = cityHandler.obtainMessage(0, weatherJson);
            cityHandler.sendMessage(msg);
        }).start();
    }

    private void updateCityList() {
        ArrayAdapter<City> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityList);
        cityListView.setAdapter(adapter);
    }
}
