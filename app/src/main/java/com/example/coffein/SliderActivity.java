package com.example.coffein;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.coffein.adapters.SliderAdapter;

public class SliderActivity extends AppCompatActivity {
    private ViewPager mSlidePager;
    private LinearLayout mDotlayout;
    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mSlidePager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotlayout = (LinearLayout) findViewById(R.id.dotsLayout);

        sliderAdapter = new SliderAdapter(this);
        mSlidePager.setAdapter(sliderAdapter);
    }
    public void GoToAuth(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("isLogin",1);
        startActivity(intent);
    }
}