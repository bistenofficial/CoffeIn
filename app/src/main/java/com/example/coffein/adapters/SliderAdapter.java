package com.example.coffein.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.coffein.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public String[] slide_headings =
            {
                    "ДОБРО\nПОЖАЛОВАТЬ\nВ COFFEEIN CLUB",
                    "КОПИТЕ БАЛЛЫ\nИ ПОЛУЧАЙТЕ\nПОДАРКИ",
                    "ЗАКАЗЫВАЙТЕ\nБЕЗ ОЧЕРЕДИ",
                    "УЧАСТВУЙТЕ\nВ АКЦИЯХ,\nВЫИГРЫВАЙТЕ\nПРИЗЫ"
            };
    public String[] slide_text =
            {
                    "Узнайте о преимуществах,\nдоступных с нашим приложением",
                    "Копите баллы с любого заказа\nи обменивайте их на любые\nпозиции из меню",
                    "Оплачивайте заказ онлайн и забирайте в удобной кофейне",
                    "Следите за нашими специальными\nакциями и конкурсами"
            };
    public int colors[] =
            {
                    Color.rgb(255, 255, 255),
                    Color.rgb(175, 205, 83),
                    Color.rgb(255, 255, 255),
                    Color.rgb(245, 163, 199)
            };
    public int[] slide_image =
        {
               R.drawable.coffee,R.drawable.coffee,R.drawable.coffee,R.drawable.coffee
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
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideText = (TextView) view.findViewById(R.id.slide_text);
        Button btn = (Button) view.findViewById(R.id.buttonGoToAuth);
        ImageView imgSlider = (ImageView) view.findViewById(R.id.imageViewSlider);

        imgSlider.setImageResource(slide_image[position]);
        slideText.setText(slide_text[position]);
        view.setBackgroundColor(colors[position]);
        slideHeading.setText(slide_headings[position]);

        if (position == 3) {
            btn.setVisibility(View.VISIBLE);
        } else {
            btn.setVisibility(View.INVISIBLE);
        }
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
