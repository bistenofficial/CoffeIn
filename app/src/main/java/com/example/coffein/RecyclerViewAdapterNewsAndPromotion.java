package com.example.coffein;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coffein.model.NewsAndPromotion;

import java.util.List;

public class RecyclerViewAdapterNewsAndPromotion extends RecyclerView.Adapter<RecyclerViewAdapterNewsAndPromotion.ViewHolder>
{
    private List<NewsAndPromotion> newsAndPromotionList;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewNAP;

        public ViewHolder(View itemView)
        {
            super(itemView);
            imageViewNAP = (ImageView)itemView.findViewById(R.id.imageViewNAP);
        }
    }
    @Override
    public RecyclerViewAdapterNewsAndPromotion.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.news_and_promotion_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RecyclerViewAdapterNewsAndPromotion.ViewHolder holder, int position) {
        // Get the data model based on position
        NewsAndPromotion contact = newsAndPromotionList.get(position);
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