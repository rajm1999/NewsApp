package com.example.newsapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.RoomDatabase;
@Database(entities = {News.class},version =1)
public  abstract class AppDataBase extends RoomDatabase {
public abstract News_DAO news_dao();
}
