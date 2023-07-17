package com.example.githubuser.utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Services {
    //get all user
    @GET("users")
    Call<List<User>> getAllUser();
    //search user
    @GET("search/users")
    Call<SearchResponse> searchUser(@Query("q") String user);

    //detail user
    @GET("users/{username}")
    Call<UserDetail> detailUser(@Path("username") String user);

    //list followers
    @GET("users/{username}/followers")
    Call<List<User>> getFollowers(@Path("username") String user);

    //list followers
    @GET("users/{username}/following")
    Call<List<User>> getFollowing(@Path("username") String user);
}
