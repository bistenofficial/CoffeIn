package com.example.coffein;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
private ViewPager mSlidePager;
private LinearLayout mDotlayout;

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
    }

    public void goToQR(View view)
    {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
    }
}