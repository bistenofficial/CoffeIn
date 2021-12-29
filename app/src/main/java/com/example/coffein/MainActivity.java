package com.example.coffein;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffein.adapters.RecyclerViewAdapterNewsAndPromotion;
import com.example.coffein.model.NewsAndPromotion;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<NewsAndPromotion> newsAndPromotionArrayList = new ArrayList<>();
    DBHelper dbHelper;
    SharedPreferences mSettings;
    String Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        Recycler();
        addTestData();
        mSettings = getSharedPreferences("my_storage", Context.MODE_PRIVATE);
        if (mSettings.getBoolean("is_logged", false)) {
            Phone = mSettings.getString("Phone", "");
        } else {
            Intent intent = new Intent(this, SliderActivity.class);
            startActivity(intent);
            finish();
        }
        UpdateInfo();
    }

    @SuppressLint("SetTextI18n")
    private void UpdateInfo() {
        TextView textViewpoints = (TextView) findViewById(R.id.textViewPoints);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select points from users where phone = ? ", new String[]{Phone});
        c.moveToFirst();
        int points = c.getInt(c.getColumnIndex("points"));
        textViewpoints.setText(points + " Coin");
        c.close();
    }

    private void Recycler() {
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.NAPRecycler);
        newsAndPromotionArrayList.add(new NewsAndPromotion("coffeepromotion"));
        newsAndPromotionArrayList.add(new NewsAndPromotion("coffee"));
        newsAndPromotionArrayList.add(new NewsAndPromotion("promotioncoffee"));
        newsAndPromotionArrayList.add(new NewsAndPromotion("coffee"));

        RecyclerViewAdapterNewsAndPromotion adapter = new RecyclerViewAdapterNewsAndPromotion(this, newsAndPromotionArrayList);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
    }

    public void goToQR(View view) {
        Intent intent = new Intent(this, Qr.class);
        startActivity(intent);
    }

    public void Exit(View view) {
        SharedPreferences mSettings = getSharedPreferences("my_storage", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putBoolean("is_logged", false).apply();
        editor.putString("Phone", "").apply();

        Intent intent = new Intent(this, SliderActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToMaps(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void addTestData() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteDatabase dw = dbHelper.getWritableDatabase();

        Cursor c = db.rawQuery("select * from coffein", new String[]{});
        if (c.getCount() == 0) {
            ContentValues cv = new ContentValues();

            cv.put("coordinate", "59.926334,30.297289");
            cv.put("name_street", "Театральная площадь, 3");
            dw.insert("coffein", null, cv);

            cv.put("coordinate", "59.978283 ,30.349302");
            cv.put("name_street", "Литовская улица, 6");
            dw.insert("coffein", null, cv);

            cv.put("coordinate", "59.833926 ,30.404899");
            cv.put("name_street", "Будапештская улица, 104к1");
            dw.insert("coffein", null, cv);
        }
        c = db.rawQuery("select * from users", new String[]{});
        if (c.getCount() == 0) {
            ContentValues cv = new ContentValues();

            cv.put("name", "Maxim");
            cv.put("phone", "+79675364663");
            cv.put("email", "maks.kirov.2002@mail.ru");
            cv.put("date_of_birth", "02-12-2002");
            dw.insert("users", null, cv);

            cv.put("name", "Tester");
            cv.put("phone", "+79675364664");
            cv.put("email", "test.ru");
            cv.put("date_of_birth", "02-02-2002");
            dw.insert("users", null, cv);
        }
        c = db.rawQuery("select * from type_menu", new String[]{});
        if (c.getCount() == 0) {
            ContentValues cv = new ContentValues();

            cv.put("name", "Холодные напитки");
            dw.insert("type_menu", null, cv);

            cv.put("name", "Горячие напитки");
            dw.insert("type_menu", null, cv);

            cv.put("name", "Выпечка и десерты");
            dw.insert("type_menu", null, cv);
        }
        c = db.rawQuery("select * from menu", new String[]{});
        if (c.getCount() == 0) {
            ContentValues cv = new ContentValues();
            cv.put("name", "Вкусный кофе");
            cv.put("info", "Кофе, который все любят");
            cv.put("price", 100);
            cv.put("type", 2);
            dw.insert("menu", null, cv);

            cv.put("name", "Очень вкусный кофе");
            cv.put("info", "Кофе, который все любят");
            cv.put("price", 150);
            cv.put("type", 2);
            dw.insert("menu", null, cv);

            cv.put("name", "Булочка с маком");
            cv.put("info", "Булочка, которую все любят");
            cv.put("price", 100);
            cv.put("type", 3);
            dw.insert("menu", null, cv);
        }

        c = db.rawQuery("select * from orders", new String[]{});
        if (c.getCount() == 0) {
            ContentValues cv = new ContentValues();

            cv.put("date", "2021-12-12 10:20:05.123");
            cv.put("id_user", 1);
            cv.put("id_cof", 1);
            dw.insert("orders", null, cv);

            cv.put("date", "2021-12-13 10:25:05.123");
            cv.put("id_user", 2);
            cv.put("id_cof", 2);
            dw.insert("orders", null, cv);

            cv.put("date", "2021-12-16 12:20:05.123");
            cv.put("id_user", 1);
            cv.put("id_cof", 1);
            dw.insert("orders", null, cv);
        }

        c = db.rawQuery("select * from order_menu", new String[]{});
        if (c.getCount() == 0) {
            ContentValues cv = new ContentValues();

            cv.put("id_order", 1);
            cv.put("id_menu", 1);
            dw.insert("order_menu", null, cv);

            cv.put("id_order", 1);
            cv.put("id_menu", 2);
            dw.insert("order_menu", null, cv);

            cv.put("id_order", 2);
            cv.put("id_menu", 2);
            dw.insert("order_menu", null, cv);

            cv.put("id_order", 3);
            cv.put("id_menu", 3);
            dw.insert("order_menu", null, cv);

            cv.put("id_order", 3);
            cv.put("id_menu", 1);
            dw.insert("order_menu", null, cv);

            cv.put("id_order", 3);
            cv.put("id_menu", 2);
            dw.insert("order_menu", null, cv);

            cv.put("id_order", 2);
            cv.put("id_menu", 1);
            dw.insert("order_menu", null, cv);

            cv.put("id_order", 1);
            cv.put("id_menu", 1);
            dw.insert("order_menu", null, cv);
        }
    }
}