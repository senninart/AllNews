package com.example.allnews;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView news_title, news_source;
    ImageView news_image;
    CardView cardView;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        news_title  = itemView.findViewById(R.id.news_title);
        news_source = itemView.findViewById(R.id.news_source);
        news_image  = itemView.findViewById(R.id.news_image);
        cardView    = itemView.findViewById(R.id.top_news_container);
    }
}
