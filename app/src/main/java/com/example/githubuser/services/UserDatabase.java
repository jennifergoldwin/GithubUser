package com.example.githubuser.services;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.githubuser.utils.User;
import com.example.githubuser.utils.UserDAO;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDAO userDao();
}
