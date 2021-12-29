package com.example.coffein;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "MyDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS users(" + "id integer primary key autoincrement," + "name text NOT NULL," + "email text," + "date_of_birth date," + "phone text NOT NULL," + "points integer DEFAULT 0" + ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS menu(" + "id integer primary key autoincrement," + "name text NOT NULL," + "info text NOT NULL," + "price integer NOT NULL," + "type integer," + "FOREIGN KEY (type) REFERENCES type (id)" + ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS orders (" + "id integer primary key autoincrement," + "date DATETIME," + "id_user integer," + "id_cof integer," + "FOREIGN KEY (id_cof) REFERENCES coffein (id)," + "FOREIGN KEY (id_user) REFERENCES users (id)"+");");
        db.execSQL("CREATE TABLE IF NOT EXISTS order_menu (" + "id_order integer," + "id_menu integer," + "FOREIGN KEY (id_order) REFERENCES orders (id)," + "FOREIGN KEY (id_menu) REFERENCES menu (id)" + ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS coffein (" + "id integer primary key autoincrement," + "coordinate text," + "name_street text" + ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS type_menu (" + "id integer primary key autoincrement," + "name text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL("drop table if exists " + TABLE_CONTACTS);
    }
}
