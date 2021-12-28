package com.example.coffein.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffein.R;
import com.example.coffein.model.NewsAndPromotion;

import java.util.List;

public class RecyclerViewAdapterNewsAndPromotion extends RecyclerView.Adapter<RecyclerViewAdapterNewsAndPromotion.ViewHolder>
{
    private final List<NewsAndPromotion> newsAndPromotionList;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewNAP;

        public ViewHolder(View itemView)
        {
            super(itemView);
            imageViewNAP = (ImageView)itemView.findViewById(R.id.imageViewNAP);
        }
    }
    @NonNull
    @Override
    public RecyclerViewAdapterNewsAndPromotion.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.news_and_promotion_item, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RecyclerViewAdapterNewsAndPromotion.ViewHolder holder, int position) {
        // Get the data model based on position
        int imageId = context.getResources().getIdentifier(newsAndPromotionList.get(position).getImg(), "drawable", context.getPackageName());
        holder.imageViewNAP .setImageResource(imageId);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return newsAndPromotionList.size();
    }
    public RecyclerViewAdapterNewsAndPromotion(Context context,List<NewsAndPromotion> newsAndPromotionList)
    {
        this.context = context;
        this.newsAndPromotionList = newsAndPromotionList;
    }
}