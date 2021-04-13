package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
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
import java.util.List;
import java.util.Map;

public class News_Main extends AppCompatActivity implements NewsItemClicked{

    private News_Adapter news_adapter;
    RecyclerView news_recyclerView;
    private ProgressBar pb;
    ArrayList<News_Data> newsArray;

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__main);
        pb = findViewById(R.id.pb);
        pb.setVisibility(View.GONE);
        newsArray = new ArrayList<>();
        news_recyclerView =(RecyclerView) findViewById(R.id.news_RecyclerView);
        news_recyclerView.setLayoutManager(layoutManager);
        news_recyclerView.setItemAnimator(new DefaultItemAnimator());
        news_recyclerView.setNestedScrollingEnabled(false);

        news_adapter = new News_Adapter(this,this::onItemClicked);
        news_recyclerView.setAdapter(news_adapter);
        Context context =this;
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork !=null && activeNetwork.isConnectedOrConnecting() && newsArray!=null ) {
            fetchData();
        }
        else{
            fetchfromRoom();
        }
    }

    private void fetchData(){
        String url ="https://saurav.tech/NewsAPI/top-headlines/category/health/in.json";
        pb.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response == null) {
                    pb.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Couldn't fetch the menu! Pleas try again.", Toast.LENGTH_LONG).show();
                    return;
                }
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
                pb.setVisibility(View.GONE);
                saveTask();
            }
          }, error -> {
            pb.setVisibility(View.GONE);
            Log.e("TAG", "Error: " + error.getMessage());
            Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
         });
        jsonObjectRequest.setShouldCache(false);
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    private void saveTask() {
        class SaveTask extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                //creating a task

                for (int i = 0; i < newsArray.size(); i++) {
                    News news= new News();
                    news.setTitle(newsArray.get(i).getTitle());
                    news.setAuthor(newsArray.get(i).getAuthor());
                    news.setImageURL(newsArray.get(i).getImageURL());
                    news.setUrl(newsArray.get(i).getUrl());
                    DatabaseClient.getInstance(getApplicationContext()).getAppDataBase().news_dao().Insert(news);
                }
                    return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }
        SaveTask st = new SaveTask();
        st.execute();
    }

    private void fetchfromRoom() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                List<News> NewsList = DatabaseClient.getInstance(getApplicationContext()).getAppDataBase().
                        news_dao().getAllData();

                newsArray.clear();

                for(News news:NewsList){
                    News_Data news_data = new News_Data(
                            news.getTitle(),
                            news.getAuthor(),
                            news.getUrl(),
                            news.getImageURL()
                            );
                    newsArray.add(news_data);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        news_adapter.updatedNews(newsArray);
                    }
                });
            }
        });
        thread.start();
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