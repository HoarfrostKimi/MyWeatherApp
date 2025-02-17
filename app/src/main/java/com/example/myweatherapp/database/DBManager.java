package com.example.myweatherapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    public static SQLiteDatabase database;
    //初始化数据库
    public static void initDB(Context context){
        CityDatabaseHelper databaseHelper=new CityDatabaseHelper(context);
        database=databaseHelper.getWritableDatabase();
    }
}
