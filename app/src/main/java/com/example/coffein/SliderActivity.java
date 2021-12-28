package com.example.coffein;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.coffein.adapters.SliderAdapter;

public class SliderActivity extends AppCompatActivity {
    private LinearLayout mDotlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_slider);
        ViewPager mSlidePager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotlayout = (LinearLayout) findViewById(R.id.dotsLayout);

        SliderAdapter sliderAdapter = new SliderAdapter(this);
        mSlidePager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlidePager.addOnPageChangeListener(changeListener);
    }

    public void addDotsIndicator(int position) {
        TextView[] mDots = new TextView[4];
        mDotlayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText("•");
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(Color.GRAY);

            mDotlayout.addView(mDots[i]);
        }
        if (mDots.length>0)
        {
            mDots[position].setTextColor(Color.BLACK);
        }
    }

    public void GoToAuth(View v) {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state)
        {

        }
    };
}