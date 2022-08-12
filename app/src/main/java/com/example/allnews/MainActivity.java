package com.example.allnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.allnews.Models.NewsApiResponse;
import com.example.allnews.Models.NewsHeadlines;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener, AdapterView.OnItemSelectedListener{

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    Spinner dropLanguage;
    String region, country = "us";
    Switch aSwitch;
    ImageView moon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.show();

        moon = findViewById(R.id.moon);
        aSwitch = findViewById(R.id.dark);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                moon.setColorFilter(getResources().getColor(R.color.navy));
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        dropLanguage = findViewById(R.id.language);
        dropLanguage.setOnItemSelectedListener(this);
        region = dropLanguage.getOnItemSelectedListener().toString();

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);

        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this);

        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(this);

        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(this);

        btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(this);


        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, country, "general", null);

    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            showNews(list);
            dialog.dismiss();
        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new CustomAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(MainActivity.this, DetailsNewsActivity.class)
                .putExtra("data", headlines));
    }


    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String category = button.getText().toString();


        if (!btn1.isEnabled()) {
            btn1.setEnabled(true);
            btn1.setTextColor(getResources().getColor(R.color.new_black));
        }
        else if (!btn2.isEnabled()) {
            btn2.setEnabled(true);
            btn2.setTextColor(getResources().getColor(R.color.new_black));
        }
        else if (!btn3.isEnabled()) {
            btn3.setEnabled(true);
            btn3.setTextColor(getResources().getColor(R.color.new_black));
        }
        else if (!btn4.isEnabled()) {
            btn4.setEnabled(true);
            btn4.setTextColor(getResources().getColor(R.color.new_black));
        }
        else if (!btn5.isEnabled()) {
            btn5.setEnabled(true);
            btn5.setTextColor(getResources().getColor(R.color.new_black));
        }
        else if (!btn6.isEnabled()) {
            btn6.setEnabled(true);
            btn6.setTextColor(getResources().getColor(R.color.new_black));
        }
        else if (!btn7.isEnabled()) {
            btn7.setEnabled(true);
            btn7.setTextColor(getResources().getColor(R.color.new_black));
        }

        if (button.isEnabled()){
            button.setEnabled(false);
            button.setTextColor(getResources().getColor(R.color.navy));
        }

        dialog.setTitle("Search Articles "+ category +"...");
        dialog.show();

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, country, category, null);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String languageItem = parent.getItemAtPosition(position).toString();
        if (languageItem.equals("United States")) {
            country = "us";
        }  else if (languageItem.equals("United Kingdom")) {
            country = "gb";
        } else if (languageItem.equals("Indonesia")) {
            country = "id";
        } else if (languageItem.equals("Brazil")) {
            country = "br";
        } else if (languageItem.equals("Japan")) {
            country = "jp";
        } else if (languageItem.equals("South Korea")) {
            country = "kr";
        } else if (languageItem.equals("Russia")) {
            country = "ru";
        } else if (languageItem.equals("Taiwan")) {
            country = "tw";
        }

        if (!languageItem.equals("Region")){

            dialog = new ProgressDialog(this);
            dialog.setTitle("Search "+country+" Articles...");
            dialog.show();

            RequestManager manager = new RequestManager(this);
            manager.getNewsHeadlines(listener, country, "general", null);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}