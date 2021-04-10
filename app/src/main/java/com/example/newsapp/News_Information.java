package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class News_Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__information);
        Bundle extras =getIntent().getExtras();
        if(extras!=null) {
            Gson gson = new Gson();
            String intentData = extras.getString("item");
            News_Data news_data = gson.fromJson(intentData, News_Data.class);
            TextView item_title = (TextView) findViewById(R.id.information_title);
            item_title.setText(news_data.getTitle());
            ImageView item_Image = (ImageView) findViewById(R.id.information_image);
            Glide.with(this).load(news_data.getImageURL()).into(item_Image);
        }

    }
}