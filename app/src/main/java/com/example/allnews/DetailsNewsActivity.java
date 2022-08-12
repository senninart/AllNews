package com.example.allnews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.allnews.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class DetailsNewsActivity extends AppCompatActivity {

    NewsHeadlines headlines;
    TextView title,author,time,detail,content;
    ImageView news_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_news);

        title   = findViewById(R.id.detail_title);
        author  = findViewById(R.id.detail_author);
        time    = findViewById(R.id.detail_time);
        detail  = findViewById(R.id.detail_detail);
        content = findViewById(R.id.detail_content);
        news_image = findViewById(R.id.detail_image);

        headlines = (NewsHeadlines) getIntent().getSerializableExtra("data");

        title.setText(headlines.getTitle());
        author.setText(headlines.getAuthor());
        time.setText(headlines.getPublishedAt());
        detail.setText(headlines.getDescription());
        content.setText(headlines.getContent());
        Picasso.get().load(headlines.getUrlToImage()).into(news_image);
    }
}