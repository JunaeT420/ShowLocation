package com.example.user.showlocation.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 10/23/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "locationDb";
    public static final String TABLE_NAME= "locationTb";
    public static final String KEY_ID = "id";
    public static final String KEY_LAT = "latitude";
    public static final String KEY_LONG= "longitude";
    Context context;
    String sq="CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY , " + KEY_LAT + " TEXT, " + KEY_LONG + " TEXT)";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sq);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertInformation(Double lat, Double longi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LAT, lat);
        values.put(KEY_LONG, longi);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }



    public List<Locations> getInformations() {
        List<Locations> modelList = new ArrayList<Locations>();

        String query = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {

            do {
                Locations model = new Locations();

                model.setLatitude(Double.valueOf(cursor.getString(1)));
                model.setLongitude(Double.valueOf(cursor.getString(2)));

                modelList.add(model);
            } while (cursor.moveToNext());
        }
        return modelList;
    }
}
