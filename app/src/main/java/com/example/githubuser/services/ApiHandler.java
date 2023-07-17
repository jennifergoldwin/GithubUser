package com.example.githubuser.services;

import androidx.room.Room;

import com.example.githubuser.utils.Services;
import com.example.githubuser.utils.User;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandler {
    private Retrofit retrofit;
    public Services services;
    public ApiHandler(){

        if (retrofit==null){
            init();
        }
    }

    void init(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create())
                .build();
        services = retrofit.create(Services.class);
    }

    void detail(){
//        Call<User> user = handler.services.detailUser("brynary");
//        user.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                Log.v("RESPONSE",response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Log.v("RESPONSEd",t.toString());
//            }
//        });
    }
}
