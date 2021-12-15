package com.example.coffein.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.coffein.R;

import org.w3c.dom.Text;

public class SliderAdapter extends PagerAdapter
{
Context context;
LayoutInflater layoutInflater;
public SliderAdapter(Context context)
{
    this.context = context;
}
public String[] slide_headings =
    {
        "Eat","Sleep"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (ConstraintLayout) o;
    }
    @Override
    public Object instantiateItem(ViewGroup container,int position)
    {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        slideHeading.setText(slide_headings[position]);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container,int position,Object object)
    {
        container.removeView((RelativeLayout)object);
    }
}
