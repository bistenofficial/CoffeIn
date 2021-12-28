package com.example.coffein;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffein.adapters.RecyclerViewAdapterNewsAndPromotion;
import com.example.coffein.model.NewsAndPromotion;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<NewsAndPromotion> newsAndPromotionArrayList = new ArrayList<>();
    private int i;
    DBHelper dbHelper;
    SharedPreferences mSettings;
    String Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mSettings = getSharedPreferences("my_storage", Context.MODE_PRIVATE);
        dbHelper = new DBHelper(this);
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.NAPRecycler);
        newsAndPromotionArrayList.add(new NewsAndPromotion("coffeepromotion"));
        newsAndPromotionArrayList.add(new NewsAndPromotion("promotioncoffee"));
        newsAndPromotionArrayList.add(new NewsAndPromotion("coffee"));

        // Create adapter passing in the sample user data
        RecyclerViewAdapterNewsAndPromotion adapter = new RecyclerViewAdapterNewsAndPromotion(this, newsAndPromotionArrayList);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        if (mSettings.getBoolean("is_logged", false)) {
            Phone = mSettings.getString("Phone", "");
        } else {
            Intent intent = new Intent(this, SliderActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void goToQR(View view) {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
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
}