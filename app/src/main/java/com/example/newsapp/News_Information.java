package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class News_Information extends AppCompatActivity {
    float x1,x2,y1,y2;
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

            TextView item_author = (TextView) findViewById(R.id.information_author);
            item_author.setText(news_data.getAuthor());

            ImageView item_Image = (ImageView) findViewById(R.id.information_image);
            Glide.with(this).load(news_data.getImageURL()).into(item_Image);

            TextView item_description = (TextView) findViewById(R.id.description);
            item_description.setText(news_data.getDescription());

            String date=news_data.getPublishedAt();
            int iend = date.indexOf("T");
            String subString;
            if(iend!=-1)
            {
                subString = date.substring(0,iend);
                TextView item_date = (TextView) findViewById(R.id.Date);
                item_date.setText(subString);
            }

            ImageButton back_btn =findViewById(R.id.back_btn);
            back_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            Button url_btn = (Button) findViewById(R.id.url_btn);
            url_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    CustomTabsIntent customTabsIntent = builder.build();
                    customTabsIntent.launchUrl(News_Information.this, Uri.parse(news_data.getUrl()));
                }
            });
        }

    }


}