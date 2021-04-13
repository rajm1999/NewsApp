package com.example.newsapp;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {
    private Context context;
    private static DatabaseClient mInstance;
    private AppDataBase appDataBase;
    private DatabaseClient(Context context){
        this.context = context;
        appDataBase = Room.databaseBuilder(context,AppDataBase.class,
                "allData").build();
    }

    public static synchronized DatabaseClient getInstance(Context context){
        if(mInstance == null){
            mInstance = new DatabaseClient(context);
        }
        return mInstance;
    }
    public AppDataBase getAppDataBase(){
        return appDataBase;
    }
}
