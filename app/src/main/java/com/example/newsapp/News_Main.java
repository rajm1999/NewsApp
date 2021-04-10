package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class News_Main extends AppCompatActivity implements NewsItemClicked{

    private News_Adapter news_adapter;
    RecyclerView news_recyclerView;
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__main);

        news_recyclerView =(RecyclerView) findViewById(R.id.news_RecyclerView);
        news_recyclerView.setLayoutManager(layoutManager);
        fetchData();
        news_adapter = new News_Adapter(this);
        news_recyclerView.setAdapter(news_adapter);
    }

    private void fetchData(){
        String url ="https://saurav.tech/NewsAPI/top-headlines/category/health/in.json";
        ArrayList<News_Data> newsArray = new ArrayList<News_Data>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try { //articles in url contains array of json object
                    JSONArray newsJsonArray = response.getJSONArray("articles");
                    for(int i=0;i< newsJsonArray.length();i++){
                        JSONObject newsJsonObject = newsJsonArray.getJSONObject(i);
                        News_Data news_data = new News_Data(                                         //Now pass this json object as a string in news_data
                                newsJsonObject.getString("title"),
                                newsJsonObject.getString("author"),
                                newsJsonObject.getString("url"),
                                newsJsonObject.getString("urlToImage")
                        );
                        newsArray.add(news_data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                news_adapter.updatedNews(newsArray);
            }
          }, error -> {
         });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onItemClicked(News_Data item) {
        Gson gson = new Gson();
        Intent intent = new Intent(News_Main.this,News_Information.class);
        String intentData =gson.toJson(item);
        intent.putExtra("item",intentData);
        this.startActivity(intent);
    }

}