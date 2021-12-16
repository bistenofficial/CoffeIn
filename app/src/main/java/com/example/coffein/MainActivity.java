package com.example.coffein;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.coffein.adapters.RecyclerViewAdapterNewsAndPromotion;
import com.example.coffein.adapters.SliderAdapter;
import com.example.coffein.model.NewsAndPromotion;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
static ArrayList<NewsAndPromotion> newsAndPromotionArrayList = new ArrayList<>();
private int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DBHelper dbHelper;
        dbHelper = new DBHelper(this);
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.NAPRecycler);
        newsAndPromotionArrayList.add(new NewsAndPromotion("coffeepromotion"));
        newsAndPromotionArrayList.add(new NewsAndPromotion("promotioncoffee"));
        newsAndPromotionArrayList.add(new NewsAndPromotion("coffee"));
        newsAndPromotionArrayList.add(new NewsAndPromotion("coffee"));
        i = getIntent().getIntExtra("isLogin",0);
        // Create adapter passing in the sample user data
        RecyclerViewAdapterNewsAndPromotion adapter = new RecyclerViewAdapterNewsAndPromotion(this,newsAndPromotionArrayList);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        if (i==0)
        {
            Intent intent = new Intent(this, SliderActivity.class);
            startActivity(intent);
            finish();
        };
    }

    public void goToQR(View view)
    {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
    }
    public void goToMaps(View view)
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}