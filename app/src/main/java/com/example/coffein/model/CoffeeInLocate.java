package com.example.coffein.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffein.DBHelper;

import java.util.ArrayList;

public class CoffeeInLocate {

    private final double latitude;
    private final double longitude;
    public CoffeeInLocate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}