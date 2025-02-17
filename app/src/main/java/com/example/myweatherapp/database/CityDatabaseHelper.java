package com.example.myweatherapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CityDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "city_database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "cities";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    public CityDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " TEXT PRIMARY KEY, " +
                COLUMN_NAME + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addCity(String cityId, String cityName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, cityId);
        values.put(COLUMN_NAME, cityName);
        db.insert(TABLE_NAME, null, values);
    }
    public List<String> getAllCityIds() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> cityIds = new ArrayList<>();
        String query = "SELECT id FROM cities";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                cityIds.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cityIds;
    }

}
