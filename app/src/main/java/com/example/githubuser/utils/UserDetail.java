package com.example.githubuser.utils;

import com.google.gson.annotations.SerializedName;

public class UserDetail extends User{
    @SerializedName("name")
    private String name;
    @SerializedName("following")
    private String following;
    @SerializedName("followers")
    private String followers;
    public UserDetail(String id, String login, String avatar_url,String name,String followers, String following) {
        super(id, login, avatar_url);
        this.name = name;
        this.followers = followers;
        this.following = following;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }
}
