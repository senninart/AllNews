package com.example.allnews;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.allnews.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class DetailsNewsActivity extends AppCompatActivity {

    NewsHeadlines headlines;
    TextView title,author,time,detail,link;
    ImageView news_image, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_news);

        title   = findViewById(R.id.detail_title);
        author  = findViewById(R.id.detail_author);
        time    = findViewById(R.id.detail_time);
        detail  = findViewById(R.id.detail_detail);
        link    = findViewById(R.id.link);
        back    = findViewById(R.id.back);
        news_image = findViewById(R.id.detail_image);

        headlines = (NewsHeadlines) getIntent().getSerializableExtra("data");

        title.setText(headlines.getTitle());
        author.setText(headlines.getAuthor());
        time.setText(headlines.getPublishedAt());
        detail.setText(headlines.getDescription());
        link.setText(headlines.getUrl());
        Picasso.get().load(headlines.getUrlToImage()).into(news_image);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linkBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(headlines.getUrl()));
                startActivity(linkBrowser);
            }
        });
    }
}