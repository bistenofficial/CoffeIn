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
import android.widget.RelativeLayout;

import com.example.coffein.model.NewsAndPromotion;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private ViewPager mSlidePager;
private LinearLayout mDotlayout;
static ArrayList<NewsAndPromotion> newsAndPromotionArrayList = new ArrayList<>();
private SliderAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

//
//        mSlidePager = (ViewPager) findViewById(R.id.slideViewPager);
//        mDotlayout = (LinearLayout) findViewById(R.id.dotsLayout);
//
//        sliderAdapter = new SliderAdapter(this);
//        mSlidePager.setAdapter(sliderAdapter);
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.NAPRecycler);
        newsAndPromotionArrayList.add(new NewsAndPromotion("coffee"));
        newsAndPromotionArrayList.add(new NewsAndPromotion("coffee"));
        // Initialize contacts
        // Create adapter passing in the sample user data
        RecyclerViewAdapterNewsAndPromotion adapter = new RecyclerViewAdapterNewsAndPromotion(this,newsAndPromotionArrayList);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
    }

    public void goToQR(View view)
    {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
    }
}