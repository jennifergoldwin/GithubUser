package com.example.githubuser.services;

import android.content.Context;

import androidx.room.Room;

import kotlin.jvm.Synchronized;

public class DbHandler {
    private Context context;
    private UserDatabase userDb;
    private static DbHandler instance;

    public DbHandler(Context context){
        this.context = context;
        userDb = Room.databaseBuilder(context, UserDatabase.class,"User.db").allowMainThreadQueries().build();
    }


    public static DbHandler getInstance(Context context) {
        if (instance==null){
            instance = new DbHandler(context);
        }
        return instance;
    }
    public UserDatabase getUserDb(){
        return userDb;
    }
}
