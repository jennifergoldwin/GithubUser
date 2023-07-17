package com.example.githubuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.githubuser.databinding.ActivityMainBinding;
import com.example.githubuser.services.ApiHandler;
import com.example.githubuser.services.adapter.ListUserAdapter;
import com.example.githubuser.utils.SearchResponse;
import com.example.githubuser.utils.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiHandler handler;
    private ActivityMainBinding binding;
    private SharedPreferences preferences;

    void init(){
        preferences = getSharedPreferences("theme",MODE_PRIVATE);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.rvListSearch.setLayoutManager(new LinearLayoutManager(this));
        handler = new ApiHandler();
        binding.flProgressbar.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(binding.getRoot());

        String theme = preferences.getString("theme","LIGHT");

        if (theme.equals("LIGHT")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        binding.toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.settings){
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            }
            else if (item.getItemId() == R.id.favorite){
                startActivity(new Intent(this, FavouriteActivity.class));
                return true;
            }
            else {
                return false;
            }
        });

        getAllData();
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.flProgressbar.setVisibility(View.VISIBLE);
                fetchData(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.v("AHA","eddd");
            }
        });



    }
    void getAllData(){
        Call<List<User>> users = handler.services.getAllUser();
        users.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                binding.flProgressbar.setVisibility(View.GONE);
                ListUserAdapter userAdapter = new ListUserAdapter(false,MainActivity.this,response.body());
                binding.rvListSearch.setAdapter(userAdapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
    void fetchData(String name){
        if (name.isEmpty()) {
            getAllData();
            return;
        }
        Call<SearchResponse> responseCall = handler.services.searchUser(name);
        responseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    binding.flProgressbar.setVisibility(View.GONE);
                    ListUserAdapter userAdapter = new ListUserAdapter(false,MainActivity.this,response.body().getItems());
                    binding.rvListSearch.setAdapter(userAdapter);
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });
    }
}